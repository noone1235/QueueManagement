package com.queuemanagementsystem.Pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "otp", schema = "public")
public class OTPInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="otp_id")
    public Integer otpId;

    @Column(name = "email")
    public String email;

    @Column(name = "otp")
    public Integer OTP;

    @Column(name="is_used")
    public boolean isUsed;

    @Column(name="created_time")
    public Timestamp createdTime;

    @Column(name="expiration_time")
    public Timestamp expirationTime;


}
