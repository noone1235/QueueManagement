package com.queuemanagementsystem.Repository;

import com.queuemanagementsystem.Pojo.OTPInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepo extends JpaRepository<OTPInfo,Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE OTPInfo otpInfo SET otpInfo.isUsed=?1 WHERE otpInfo.otpId=?2")
    void updateIsUsed(boolean is_used,Integer otpId);


    @Query("SELECT o FROM OTPInfo o where o.email=?1 and \n" +
            "coalesce(o.isUsed,false)=false \n" +
            "and (timezone('utc', now()))<o.expirationTime " +
            "ORDER BY o.otpId DESC LIMIT 1")
    OTPInfo findByEmail(String email);
}
