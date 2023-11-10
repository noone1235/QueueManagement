package com.queuemanagementsystem.Repository;

import com.queuemanagementsystem.Pojo.EndUserInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EndUserRepo extends JpaRepository<EndUserInfo,Integer> {

    @Query("SELECT MAX(eu.tokenNumber) from EndUserInfo eu where eu.queueId=?1 ")
    Integer getHighestTokenNumber(int queueId);
}
