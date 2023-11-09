package com.queuemanagementsystem.Pojo;

import lombok.Data;

@Data
public class RegisterOrganizationInfo {
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String organizationName;
    private String email;
    private String password;
}
