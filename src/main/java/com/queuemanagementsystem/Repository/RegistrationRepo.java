package com.queuemanagementsystem.Repository;

import com.queuemanagementsystem.Pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepo extends JpaRepository<UserInfo,Integer> {
}
