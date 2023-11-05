package com.queuemanagementsystem.Repository;

import com.queuemanagementsystem.Pojo.QueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationQueueRepo extends JpaRepository<QueueEntity,Integer> {
    List<QueueEntity> findAllByQueueStatusIsTrue();

}
