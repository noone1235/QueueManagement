package com.queuemanagementsystem.Pojo;


import com.queuemanagementsystem.Enum.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
@Data
@Entity
@Table(name="authentication",schema="public")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="user_role")
    private UserRole userRole;
    @Column(name="user_password")
    private String userPassword;
    @Column(name="user_email")
    private String userEmail;
    @Column(name="phone_number")
    private long phoneNumber;
    @Column(name="is_deleted")
    private boolean isDeleted;

}
