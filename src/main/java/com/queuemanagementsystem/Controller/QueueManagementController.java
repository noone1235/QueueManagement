package com.queuemanagementsystem.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.queuemanagementsystem.Enum.UserRole;
import com.queuemanagementsystem.Pojo.OTPInfo;
import com.queuemanagementsystem.Pojo.QueueEntity;
import com.queuemanagementsystem.Pojo.CreateTokenForQueueRequest;
import com.queuemanagementsystem.Pojo.UserInfo;
import com.queuemanagementsystem.Repository.AuthenticationRepo;
import com.queuemanagementsystem.Repository.OtpRepo;
import com.queuemanagementsystem.Service.QueueManagementService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.*;

@RestController
@RequestMapping("/endUser")
@Slf4j
public class QueueManagementController {

    //QueueAPIs -- Customer level
    //Backend usageAPI--mostly come in service

    private static QueueManagementService queueManagementService;

    private static OtpRepo otpRepo;

    private static AuthenticationRepo authRepo;
    @Autowired
    public QueueManagementController(QueueManagementService queueManagementService,OtpRepo otpRepo,AuthenticationRepo authRepo){
        this.queueManagementService=queueManagementService;
        this.otpRepo=otpRepo;
        this.authRepo=authRepo;
    }

    @PostMapping("/userEmailOnboarding")
    public ResponseEntity<String> emailOnboarding(@RequestParam String email){
        try {
            Random rand = new Random();
            int otp = rand.nextInt(100000, 999999);

            OTPInfo otpInfo = new OTPInfo();
            otpInfo.setEmail(email);
            otpInfo.setOTP(otp);
            otpRepo.save(otpInfo);
        }
        catch(Exception databaseException){
            log.error(databaseException.getLocalizedMessage(),databaseException);
            return ResponseEntity.internalServerError().body(databaseException.getLocalizedMessage());
        }
        return ResponseEntity.ok().body("email sent");
    }

    @PostMapping("/emailValidation")
    public ResponseEntity<String> emailValidation(@RequestBody OTPInfo otpInfo){
        try {
            if (queueManagementService.emailValidation(otpInfo)) {
                UserInfo userInfo = new UserInfo();
                userInfo.setUserEmail(otpInfo.getEmail());
                userInfo.setUserRole(UserRole.END_USER);
                userInfo.setDeleted(false);
                authRepo.save(userInfo);
            }
            else{
                return ResponseEntity.ok().body("False");
            }
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
        return ResponseEntity.ok().body("True");
    }
    @GetMapping("/getQueuesOfAnUser")
    public List<QueueEntity> getQueuesOfAnUser(@RequestParam Map<String,Integer> request){
        //write a query and send it to JDBC wrapper
        List<QueueEntity> queues=new LinkedList<>();
        try {
            queues=queueManagementService.getQueuesOfAnUser(request.get("userId"));
        }
        catch (Exception databaseException){
            log.error(databaseException.getLocalizedMessage(),databaseException);
        }
        return queues;
    }

    @GetMapping("/getQueuesDetails")
    public QueueEntity getQueueDetails(int queueId){
        return queueManagementService.getQueueDetails(queueId);
    }

    @GetMapping("getCurrentTokenNumber")
    public int getCurrentTokenNumber(@RequestParam Integer queueId){
        return queueManagementService.getCurrentTokenForaQueue(queueId);
    }

    @PostMapping("/createTokenForQueue")
    public int createTokenForQueue(@RequestBody CreateTokenForQueueRequest createTokenForQueueRequest, HttpServletRequest httpRequest){
        return queueManagementService.createTokenForQueue(createTokenForQueueRequest,httpRequest.getRemoteUser());
    }

}
