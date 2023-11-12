package com.queuemanagementsystem.Repository;

import com.queuemanagementsystem.Pojo.QueueEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrganizationQueueRepo extends JpaRepository<QueueEntity,Integer> {
    @Query("SELECT queueEntity FROM QueueEntity queueEntity WHERE queueEntity.organizationId=?2 AND queueEntity.queueStatus=?1")
    List<QueueEntity> findByQueueStatusAndOrganizationId(boolean status,Integer organizationId);

    @Modifying
    @Transactional
    @Query("UPDATE QueueEntity q SET q.queueStatus = ?1 where q.queueId=?2")
    void updateQueueStatus(boolean queueStatus,String queueId);
}
