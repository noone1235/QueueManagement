package com.queuemanagementsystem.Pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name="register_table",schema="public")
public class RegisterRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="register_id")
    public Integer id;

    @Column(name="organization_name")
    public String organizationName;

    @Column(name="password")
    public String password;

    @Column(name="phone_number")
    public long phoneNumber;

    @Column(name="email")
    public String email;

    @Column(name="created_time")
    public Timestamp createdTime;

    @Column(name="last_name")
    public String lastName;

    @Column(name="first_name")
    public String firstName;
}
