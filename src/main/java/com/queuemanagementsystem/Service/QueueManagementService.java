package com.queuemanagementsystem.Service;

import com.queuemanagementsystem.Pojo.EndUserInfo;
import com.queuemanagementsystem.Pojo.QueueEntity;
import com.queuemanagementsystem.Pojo.CreateTokenForQueueRequest;
import com.queuemanagementsystem.Pojo.RealtimeQueueInfo;
import com.queuemanagementsystem.Repository.EndUserRepo;
import com.queuemanagementsystem.Repository.OrganizationQueueRepo;
import com.queuemanagementsystem.Repository.QueueRepo;
import com.queuemanagementsystem.Repository.RealtimeQueueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;

@Service
public class QueueManagementService {
    private static QueueRepo queueRepo;

    private static OrganizationQueueRepo orgQueueRepo;

    private static EndUserRepo endUserRepo;
    private static RealtimeQueueRepo realtimeQueueRepo;
    @Autowired
    public QueueManagementService(QueueRepo queueRepo, OrganizationQueueRepo orgQueueRepo, RealtimeQueueRepo realTimeQueueRepo,EndUserRepo endUserRepo){
        this.queueRepo=queueRepo;
        this.orgQueueRepo=orgQueueRepo;
        this.realtimeQueueRepo = realTimeQueueRepo;
        this.endUserRepo = endUserRepo;
    }

    public Map<String,Object> getQueuesOfAnUser(Integer user_id){
        //write a query and send it to JDBC wrapper
        return null;
    }

    public QueueEntity getQueueDetails(int queueId){
        return orgQueueRepo.findById(queueId).get();
    }


    public int createTokenForQueue(CreateTokenForQueueRequest createTokenForQueueRequest, String user){
        RealtimeQueueInfo queueInfo=realtimeQueueRepo.findByQueueIdAndOrganizationId(createTokenForQueueRequest.getQueueId(), createTokenForQueueRequest.getOrganizationId()).get();
        int tokenNumber=queueInfo.getHighestTokenNumber()+1;
        realtimeQueueRepo.updateHighestTokenNumber(tokenNumber);
        EndUserInfo endUserInfo=new EndUserInfo();
        endUserInfo.setCreatedBy(user);
        endUserInfo.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        endUserInfo.setQueueId(createTokenForQueueRequest.getQueueId());
        endUserInfo.setTokenNumber(tokenNumber);
        endUserInfo.setUserId(createTokenForQueueRequest.getUser_id());
        return tokenNumber;
    }

}
