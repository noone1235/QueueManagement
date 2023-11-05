package com.queuemanagementsystem.Service;

import com.queuemanagementsystem.Enum.UserRole;
import com.queuemanagementsystem.Pojo.RegisterOrganizationInfo;
import com.queuemanagementsystem.Pojo.UserInfo;
import com.queuemanagementsystem.Repository.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class RegistrationService {

    @Autowired
    RegistrationRepo registerRepo;
    public boolean registerOrganization(RegisterOrganizationInfo registerInfo){
        UserInfo userInfo=new UserInfo();

        userInfo.setFirstName(registerInfo.getFirstName());
        userInfo.setLastName(registerInfo.getLastName());
        userInfo.setUserEmail(registerInfo.getEmail());
        userInfo.setPhoneNumber(registerInfo.getPhoneNumber());
        userInfo.setOrganizationName(registerInfo.getOrganizationName());
        userInfo.setUserPassword(registerInfo.getPassword());
        userInfo.setDeleted(false);
        userInfo.setUserRole(UserRole.ORGANIZATION_USER);
        userInfo.setCreatedBy(userInfo.getFirstName());
        userInfo.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        registerRepo.save(userInfo);
        return false;
    }
}
