package com.queuemanagementsystem.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.queuemanagementsystem.Pojo.EndUserInfo;
import com.queuemanagementsystem.Pojo.QueueEntity;
import com.queuemanagementsystem.Pojo.CreateTokenForQueueRequest;
import com.queuemanagementsystem.Pojo.RealtimeQueueInfo;
import com.queuemanagementsystem.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class QueueManagementService {
    private static OrganizationQueueRepo orgQueueRepo;

    private static EndUserRepo endUserRepo;
    private static RealtimeQueueRepo realtimeQueueRepo;

    private static JDBCWrapper jdbcWrapper;
    @Autowired
    public QueueManagementService(OrganizationQueueRepo orgQueueRepo, RealtimeQueueRepo realTimeQueueRepo,EndUserRepo endUserRepo,JDBCWrapper jdbcWrapper){
        this.orgQueueRepo=orgQueueRepo;
        this.realtimeQueueRepo = realTimeQueueRepo;
        this.endUserRepo = endUserRepo;
        this.jdbcWrapper=jdbcWrapper;
    }

    public List<QueueEntity> getQueuesOfAnUser(Integer user_id){
        //write a query and send it to JDBC wrapper
        List<QueueEntity> queues=new LinkedList<>();
        ObjectMapper mapper=new ObjectMapper();
        try {
            jdbcWrapper.openConnection();
            ResultSet resultSet=jdbcWrapper.executeQuery("SELECT * FROM public.organizationqueue orgQueue " +
                    "LEFT JOIN (SELECT * FROM public.enduser where user_id=?) eu " +
                    "ON orgQueue.queue_id=eu.queue_id",user_id);
            while(resultSet.next()){
                QueueEntity queue=mapper.readValue(mapper.writeValueAsString(resultSet),QueueEntity.class);
                queues.add(queue);
            }
            jdbcWrapper.closeConnection();
        }
        catch (Exception databaseException){

        }
        return queues;
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
