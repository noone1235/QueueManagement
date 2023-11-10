package com.queuemanagementsystem.Service;

import com.queuemanagementsystem.Pojo.*;
import com.queuemanagementsystem.Repository.OrganizationRepo;
import com.queuemanagementsystem.Repository.RegistrationRepo;
import com.queuemanagementsystem.Repository.UserOrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    RegistrationRepo registerRepo;

    @Autowired
    OrganizationRepo organizationRepo;

    @Autowired
    UserOrganizationRepo userOrganizationRepo;
    public RegisterRequest registerOrganization(RegisterRequest registerInfo){
        return registerRepo.save(registerInfo);
    }

    public OrganizationInfo saveOrganization(OrganizationInfo organizationInfo){
        return organizationRepo.save(organizationInfo);
    }

    public UserOrganization saveUserOrganization(UserOrganization userOrganization){
        return userOrganizationRepo.save(userOrganization);
    }

    public OrganizationInfo getOrganization(String organizationName){
        return organizationRepo.findByOrganizationName(organizationName);
    }
}
