package com.queuemanagementsystem.Service;

import com.queuemanagementsystem.Pojo.CreateQueueRequest;
import com.queuemanagementsystem.Pojo.GroupInfo;
import com.queuemanagementsystem.Pojo.QueueEntity;
import com.queuemanagementsystem.Repository.GroupRepo;
import com.queuemanagementsystem.Repository.OrganizationQueueRepo;
import com.queuemanagementsystem.Repository.SubGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrganizationQueueService {
    @Autowired
    OrganizationQueueRepo organizationQueueRepo;

    @Autowired
    GroupRepo groupRepo;

    @Autowired
    SubGroupRepo subGroupRepo;

    public void createQueueInfo(CreateQueueRequest queueRequest){


        QueueEntity queueInfo=new QueueEntity();
        queueInfo.setQueueEndTime(queueRequest.getQueueEndTime());
        queueInfo.setQueueStatus(true);
        queueInfo.setQueueSize(queueRequest.getQueueSize());
        queueInfo.setQueueName(queueRequest.getQueueName());
        queueInfo.setTokenType(queueRequest.getTokenType());
        //queueInfo.setGroupId(queueRequest.getTokenGroup());
        queueInfo.setQueueSize(queueRequest.getQueueSize());
        queueInfo.setQueueSize(queueRequest.getQueueSize());

        GroupInfo grpInfo =new GroupInfo();
        grpInfo.setGroupName(queueRequest.getTokenGroup());
        groupRepo.save(grpInfo);
//        queueInfo.setTokenType();
        //set all the fields and save the entity to database
    }

    public Map<String,Object> getActiveQueues(){
        Map<String,Object> getQueueResponse=new HashMap<>();
        List<QueueEntity> activeQueues=organizationQueueRepo.findAllByQueueStatusIsTrue();
        //fetch all memebers of the queue and add the key to getQueueResponse

        return getQueueResponse;
    }
    public void updateQueueInfo(CreateQueueRequest queueRequest){
        //set all fields and update the entity to database, check for edge cases in this scenario
    }
    public void deleteQueueInfo(int queueId){
        //delete queue bases on id return success or failure
        organizationQueueRepo.deleteById(queueId);
    }

    public void updateQueueStatus(boolean queueStatus,String queueId){
        //write a update query
//        organizationQueueRepo.updateByQOrQueueStatus(queueStatus);
        organizationQueueRepo.updateQueueStatus(queueStatus,queueId);

    }

}
