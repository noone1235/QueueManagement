package com.queuemanagementsystem.Controller;

import com.queuemanagementsystem.Pojo.QueueEntity;
import com.queuemanagementsystem.Pojo.CreateTokenForQueueRequest;
import com.queuemanagementsystem.Service.QueueManagementService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class QueueManagementController {

    //QueueAPIs -- Customer level
    //Backend usageAPI--mostly come in service

    private static QueueManagementService queueManagementService;
    @Autowired
    public QueueManagementController(QueueManagementService queueManagementService){
        this.queueManagementService=queueManagementService;
    }

    @GetMapping("/getQueuesOfAnUser")
    public List<QueueEntity> getQueuesOfAnUser(int user_id){
        return queueManagementService.getQueuesOfAnUser(user_id);
    }

    @GetMapping("/getQueuesDetails")
    public QueueEntity getQueueDetails(int queueId){
        return queueManagementService.getQueueDetails(queueId);
    }

    @PostMapping("/createTokenForQueue")
    public int createTokenForQueue(@RequestBody CreateTokenForQueueRequest createTokenForQueueRequest, HttpServletRequest httpRequest){
        return queueManagementService.createTokenForQueue(createTokenForQueueRequest,httpRequest.getRemoteUser());
    }




}
