package com.queuemanagementsystem.Service;

import com.queuemanagementsystem.Pojo.*;
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

        GroupInfo grpInfo =new GroupInfo();
        grpInfo.setGroupName(queueRequest.getTokenGroup());
        grpInfo.setOrganizationId(queueRequest.getOrganizationId());
        GroupInfo grpInfo1=groupRepo.save(grpInfo);

        SubGroupInfo subGroupInfo =new SubGroupInfo();
        subGroupInfo.setGroupId(grpInfo.getGroupId());
        subGroupInfo.setSubGroupName(queueRequest.getTokenSubGroup());
        SubGroupInfo subGroupInfo1=subGroupRepo.save(subGroupInfo);

        QueueEntity queueInfo=new QueueEntity();
        queueInfo.setQueueEndTime(queueRequest.getQueueEndTime());
        queueInfo.setQueueStatus(true);
        queueInfo.setQueueId(grpInfo1.getGroupId());
        queueInfo.setGroupId(grpInfo.getGroupId());
        queueInfo.setSubGroupId(subGroupInfo1.getSub_group_id());
        queueInfo.setQueueSize(queueRequest.getQueueSize());
        queueInfo.setQueueName(queueRequest.getQueueName());
        queueInfo.setTokenType(queueRequest.getTokenType());
        queueInfo.setQueueSize(queueRequest.getQueueSize());
        queueInfo.setQueueSize(queueRequest.getQueueSize());
        organizationQueueRepo.save(queueInfo);

    }

    public List<QueueEntity> getActiveQueues(Integer organizationId){
        List<QueueEntity> activeQueues=organizationQueueRepo.findByQueueStatusAndOrganizationId(true,organizationId);
        //fetch all memebers of the queue and add the key to getQueueResponse
        return activeQueues;
    }
    public void updateQueueInfo(CreateQueueRequest queueRequest){
        //set all fields and update the entity to database, check for edge cases in this scenario

    }
    public void deleteQueueInfo(int queueId){
        //delete queue bases on id return success or failure
        organizationQueueRepo.deleteById(queueId);
    }

    public void updateQueueStatus(boolean queueStatus,String queueId){
        organizationQueueRepo.updateQueueStatus(queueStatus, queueId);
    }

}
