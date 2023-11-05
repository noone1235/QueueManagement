package com.queuemanagementsystem.Pojo;


import com.queuemanagementsystem.Enum.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;
@Data
@Entity
@Table(name="",schema="")
public class UserInfo {
    @Id
    @Column(name="user_id")
    private int userId;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="organization_id")
    private String organizationId;
    @Column(name="organization_name")
    private String organizationName;
    @Column(name="user_role")
    private UserRole userRole;
    @Column(name="user_password")
    private String userPassword;
    @Column(name="user_email")
    private String userEmail;
    @Column(name="phone_number")
    private int phoneNumber;
    @Column(name="is_deleted")
    private boolean isDeleted;
    @Column(name="created_by")
    private String createdBy;
    @Column(name="created_date")
    private Timestamp createdDate;
    @Column(name="modified_by")
    private String modifiedBy;
    @Column(name="modified_date")
    private Timestamp modifiedDate;
}
