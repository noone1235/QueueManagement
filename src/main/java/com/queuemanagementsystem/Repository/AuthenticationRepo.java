package com.queuemanagementsystem.Repository;

import com.queuemanagementsystem.Pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthenticationRepo extends JpaRepository<UserInfo,Integer> {
    @Query("SELECT userInfo FROM UserInfo userInfo WHERE userInfo.userEmail=?1")
    UserInfo findByEmail(String userEmail);

}
