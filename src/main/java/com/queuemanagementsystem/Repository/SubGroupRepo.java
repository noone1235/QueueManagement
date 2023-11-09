package com.queuemanagementsystem.Repository;

import com.queuemanagementsystem.Pojo.subGroupInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubGroupRepo extends JpaRepository<subGroupInfo,Integer> {
}
