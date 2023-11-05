package com.queuemanagementsystem.Repository;

import com.queuemanagementsystem.Pojo.EndUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EndUserRepo extends JpaRepository<EndUserInfo,Integer> {
}
