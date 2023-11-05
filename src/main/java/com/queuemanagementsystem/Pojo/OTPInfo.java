package com.queuemanagementsystem.Pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "", schema = "")
public class OTPInfo {

    @Column(name = "email")
    public String email;

    @Column(name = "otp")
    public String OTP;

}
