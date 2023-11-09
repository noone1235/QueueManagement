package com.queuemanagementsystem.Service;

import com.queuemanagementsystem.Enum.UserRole;
import com.queuemanagementsystem.Pojo.RegisterOrganizationInfo;
import com.queuemanagementsystem.Pojo.RegisterRequest;
import com.queuemanagementsystem.Pojo.UserInfo;
import com.queuemanagementsystem.Repository.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class RegistrationService {

    @Autowired
    RegistrationRepo registerRepo;
    public boolean registerOrganization(RegisterRequest registerInfo){
        registerRepo.save(registerInfo);
        return false;
    }
}
