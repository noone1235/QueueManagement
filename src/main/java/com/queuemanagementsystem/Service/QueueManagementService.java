package com.queuemanagementsystem.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.queuemanagementsystem.Pojo.*;
import com.queuemanagementsystem.Repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
public class QueueManagementService {
    private static OrganizationQueueRepo orgQueueRepo;

    private static EndUserRepo endUserRepo;
    private static RealtimeQueueRepo realtimeQueueRepo;

    private static OtpRepo otpRepo;
    private static JDBCWrapper jdbcWrapper;
    @Autowired
    public QueueManagementService(OrganizationQueueRepo orgQueueRepo,OtpRepo otpRepo, RealtimeQueueRepo realTimeQueueRepo,EndUserRepo endUserRepo,JDBCWrapper jdbcWrapper){
        this.orgQueueRepo=orgQueueRepo;
        this.realtimeQueueRepo = realTimeQueueRepo;
        this.endUserRepo = endUserRepo;
        this.jdbcWrapper=jdbcWrapper;
        this.otpRepo=otpRepo;
    }



    public boolean emailValidation(OTPInfo otpInfo){
        OTPInfo dbOtpInfo=otpRepo.getReferenceById(otpInfo.getEmail());
        return dbOtpInfo.getOTP().equals(otpInfo.getOTP());
    }


    public List<QueueEntity> getQueuesOfAnUser(Integer user_id){
        //write a query and send it to JDBC wrapper
        List<QueueEntity> queues=new LinkedList<>();
        ObjectMapper mapper=new ObjectMapper();
        try {
            jdbcWrapper.openConnection();
            ResultSet resultSet=jdbcWrapper.executeQuery("SELECT * FROM public.organization_queue orgQueue " +
                    "LEFT JOIN (SELECT * FROM public.end_user where user_id=?) eu " +
                    "ON orgQueue.queue_id=eu.queue_id",user_id);
            while(resultSet.next()){
                QueueEntity queue=mapper.readValue(mapper.writeValueAsString(resultSet),QueueEntity.class);
                queues.add(queue);
            }
            jdbcWrapper.closeConnection();
        }
        catch (Exception databaseException){
            log.error(databaseException.getLocalizedMessage(),databaseException);
        }
        return queues;
    }


    public QueueEntity getQueueDetails(int queueId){
        return orgQueueRepo.findById(queueId).get();
    }


    public int createTokenForQueue(CreateTokenForQueueRequest createTokenForQueueRequest, String user){
        RealtimeQueueInfo queueInfo=realtimeQueueRepo.findByQueueId(createTokenForQueueRequest.getQueueId()).get();
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

    public int getCurrentTokenForaQueue(Integer queueId){
        return realtimeQueueRepo.getReferenceById(queueId).getCurrentTokenNumber();
    }
}
