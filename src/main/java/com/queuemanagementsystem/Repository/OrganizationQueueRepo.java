package com.queuemanagementsystem.Repository;

import com.queuemanagementsystem.Pojo.QueueEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrganizationQueueRepo extends JpaRepository<QueueEntity,Integer> {
    List<QueueEntity> findByQueueStatus(boolean status);

    @Modifying
    @Transactional
    @Query("UPDATE QueueEntity q SET q.queueStatus = ?1 where q.queueId=?2")
    void updateQueueStatus(boolean queueStatus,String queueId);
}
