package com.queuemanagementsystem.Repository;

import com.queuemanagementsystem.Pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepo extends JpaRepository<UserInfo,Integer> {
}
