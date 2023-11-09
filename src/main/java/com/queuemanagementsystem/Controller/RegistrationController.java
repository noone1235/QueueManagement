package com.queuemanagementsystem.Controller;

import com.queuemanagementsystem.Pojo.RegisterOrganizationInfo;
import com.queuemanagementsystem.Pojo.RegisterRequest;
import com.queuemanagementsystem.Repository.OrganizationQueueRepo;
import com.queuemanagementsystem.Service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/registration")
public class RegistrationController {

    public static final String SUCCES_MESSAGE="Registration successfull";

    @Autowired
    RegistrationService registerService;

    @PostMapping("/registerOrganization")
    public String registerOrganization(@RequestBody RegisterRequest registerRequest){
        try{
            registerService.registerOrganization(registerRequest);

        }
        catch (Exception databaseException) {
            log.error(databaseException.getLocalizedMessage(), databaseException);
            throw databaseException;
        }
        return SUCCES_MESSAGE;
    }

//    public String OTP
}
