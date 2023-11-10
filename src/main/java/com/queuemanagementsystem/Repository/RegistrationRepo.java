package com.queuemanagementsystem.Repository;

import com.queuemanagementsystem.Pojo.RegisterRequest;
import com.queuemanagementsystem.Pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegistrationRepo extends JpaRepository<RegisterRequest,Integer> {

    @Query("SELECT registerRequest.organizationName from RegisterRequest registerRequest where registerRequest.id=?1")
    String findByRegisterId(Integer registerId);
}