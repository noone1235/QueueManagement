package com.queuemanagementsystem.Repository;

import com.queuemanagementsystem.Pojo.subGroupInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface subGroupRepo extends JpaRepository<subGroupInfo,Integer> {
}