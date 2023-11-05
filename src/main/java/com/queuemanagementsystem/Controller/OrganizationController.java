package com.queuemanagementsystem.Controller;

import com.queuemanagementsystem.Pojo.CreateQueueRequest;
import com.queuemanagementsystem.Service.OrganizationQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
public class OrganizationController {
    //QueueAPIS -- Organization level
    @Autowired
    OrganizationQueueService organizationQueueService;

    @PostMapping("/createQueueInfo")
    public boolean createQueueInfo(@RequestBody CreateQueueRequest queueInfo) throws Exception {
        try{
            organizationQueueService.createQueueInfo(queueInfo);
        }
        catch(Exception databaseException){
            log.error(databaseException.getLocalizedMessage(),databaseException);
            throw new Exception("DataBase Exception");
        }
        return true;
    }
    @GetMapping("/getActiveQueues")
    public Map<String,Object> getActiveQueues() throws Exception {
        try{
            return organizationQueueService.getActiveQueues();
        }
        catch(Exception databaseException) {
            log.error(databaseException.getLocalizedMessage(), databaseException);
            throw new Exception("DataBase Exception");
        }
    }
    @PutMapping("/updateQueueInfo")
    public boolean updateQueueInfo(@RequestBody CreateQueueRequest queueInfo) throws Exception {
        try{
            organizationQueueService.updateQueueInfo(queueInfo);
        }
        catch(Exception databaseException){
            log.error(databaseException.getLocalizedMessage(),databaseException);
            throw new Exception("DataBase Exception");
        }
        return true;
    }
    @PutMapping("/updateQueue")
    public boolean updateQueue(String QueueId,String OrganizationId){
        //Update below three tables
        //EndUserInfo
        //REalTimeQueue
        //REasonTable
        try{

        }
        catch(Exception databaseException){
            //Exception scenarios
            //1.EndUserInfo not updated
            //2.RealTimeQueue not update but EndUserInfo updated
            //ReasonTable not updated but above two updated
        }
        return true;
    }

    @PutMapping("/updateQueueStatus")
    public boolean createQueue(@RequestBody boolean queueStatus) throws Exception {
        try{
            organizationQueueService.updateQueueStatus(queueStatus);
        }
        catch(Exception databaseException){
            log.error(databaseException.getLocalizedMessage(),databaseException);
            throw new Exception("DataBase Exception");
        }
        return true;
    }
    @DeleteMapping ("/deleteQueueInfo")
    public boolean deleteQueue(int queueId) throws Exception {
        try{
            organizationQueueService.deleteQueueInfo(queueId);
        }
        catch(Exception databaseException){
            log.error(databaseException.getLocalizedMessage(),databaseException);
            throw new Exception("DataBase Exception");
        }
        return true;
    }
    @PostMapping("/callToken")
    public boolean callToken(String Token){
        try{
            //calls token as specified meaning create a real time Queue table
            //
        }
        catch(Exception e){

        }
        return true;
    }

    @PostMapping("/markAsProcessed")
    public boolean markAsProcessed(){
        //should update the real time queue table , end user table, reason table
        //check exception case scenarios
        return true;
    }
    //end the queue if the queue has not moved for 2hrs
    //Also consider the scenario where server crashes or the application crashes
    @PostMapping("/endQueueForCurrent")
    public boolean endQueueForCurrent(){
        try {
            //update all the end user Info, make the current token number as 1 and so on
            //RealTimeQueue all gets updated accordingly
            //This api should return a message to all the users of the queue that it got updated for the next queue session.

        }
        catch(Exception databaseException){
            //
        }
        return false;
    }
}
