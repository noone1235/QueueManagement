package com.queuemanagementsystem.Repository;

import com.queuemanagementsystem.Pojo.RealtimeQueueInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueRepo extends JpaRepository<RealtimeQueueInfo,String> {
}
