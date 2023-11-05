package com.queuemanagementsystem.Repository;

import com.queuemanagementsystem.Pojo.RealtimeQueueInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Repository
public interface RealtimeQueueRepo extends JpaRepository<RealtimeQueueInfo,Integer> {

    Optional<RealtimeQueueInfo> findByQueueIdAndOrganizationId(Integer queueId, Integer organizationId);

    @Modifying
    @Transactional
    @Query("UPDATE RealtimeQueueInfo queueInfo SET queueInfo.highestTokenNumber=?1")
    void updateHighestTokenNumber(int highestTokenNumber);
}
