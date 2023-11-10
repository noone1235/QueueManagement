package com.queuemanagementsystem.Controller;

import com.queuemanagementsystem.Enum.UserRole;
import com.queuemanagementsystem.Pojo.*;
import com.queuemanagementsystem.Repository.AuthenticationRepo;
import com.queuemanagementsystem.Repository.OtpRepo;
import com.queuemanagementsystem.Repository.RegistrationRepo;
import com.queuemanagementsystem.Service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.Digest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Random;

@RestController
@Slf4j
@RequestMapping("/registration")
public class RegistrationController {

    public static final String SUCCES_MESSAGE="Registration successfull";

    @Autowired
    RegistrationService registerService;

    @Autowired
    OtpRepo otpRepo;

    @Autowired
    RegistrationRepo registrationRepo;

    @Autowired
    AuthenticationRepo authenticationRepo;
    @PostMapping("/registerOrganization")
    public ResponseEntity<Object> registerOrganization(@RequestBody RegisterRequest registerRequest) throws Exception {
        RegisterRequest savedRegisterRequest=new RegisterRequest();
        try{
            savedRegisterRequest=registerService.registerOrganization(registerRequest);

            Random rand=new Random();
            int otp=rand.nextInt(100000,999999);
            //have to send to email
            otpRepo.save(new OTPInfo(null,registerRequest.getEmail(),otp,false,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()+3600000)));
        }
        catch (Exception databaseException) {
            log.error(databaseException.getLocalizedMessage(), databaseException);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(databaseException.getLocalizedMessage());
        }
        return ResponseEntity.ok().body(savedRegisterRequest);
    }

    @PostMapping("/emailValidation")
    public ResponseEntity<String> emailValidation(@RequestBody OTPInfo otpInfo, @RequestParam Integer registerID){

        try {
            OTPInfo dbOtpInfo = otpRepo.findByEmail(otpInfo.getEmail());

            if(!dbOtpInfo.getOTP().equals(otpInfo.getOTP())){
                throw new Exception("false");
            }
            else{
                otpRepo.updateIsUsed(true,dbOtpInfo.getOtpId());
            }

            //insert Organization Table
            String organizationName=registrationRepo.findByRegisterId(registerID);
            //checks if the organization is present in current records
            OrganizationInfo organizationInfo=registerService.getOrganization(organizationName);
            //if not present add it to database
            if(organizationInfo==null) {
                organizationInfo = registerService.saveOrganization(new OrganizationInfo(null, organizationName));
            }

            //Authentication Table
            RegisterRequest registrationInfo=registrationRepo.findById(registerID).get();
            UserInfo userInfo=new UserInfo();
            userInfo.setUserRole(UserRole.ORGANIZATION_USER);
            userInfo.setPhoneNumber(registrationInfo.getPhoneNumber());
            userInfo.setFirstName(registrationInfo.getFirstName());
            userInfo.setDeleted(false);
            userInfo.setLastName(registrationInfo.getLastName());
            userInfo.setUserPassword(Sha512DigestUtils.shaHex(registrationInfo.getPassword()));
            UserInfo savedUserInfo=authenticationRepo.save(userInfo);

            //insert save UserOrganization
            registerService.saveUserOrganization(new UserOrganization(savedUserInfo.getUserId(), organizationInfo.getOrganizationId()));

            return ResponseEntity.ok("true");
        }
        catch(Exception exception){
            log.error(exception.getLocalizedMessage(),exception);
            return ResponseEntity.internalServerError().body(exception.getLocalizedMessage());
        }
    }
}
