package com.queuemanagementsystem.Repository;

import com.queuemanagementsystem.Pojo.QueueInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueRepository extends JpaRepository<QueueInfo,String> {
}
