package com.queuemanagementsystem.Controller;

import com.queuemanagementsystem.Pojo.CreateQueueRequest;
import com.queuemanagementsystem.Pojo.QueueEntity;
import com.queuemanagementsystem.Pojo.RealtimeQueueInfo;
import com.queuemanagementsystem.Repository.EndUserRepo;
import com.queuemanagementsystem.Repository.OrganizationQueueRepo;
import com.queuemanagementsystem.Repository.RealtimeQueueRepo;
import com.queuemanagementsystem.Service.OrganizationQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/organization")
public class OrganizationController {
    //QueueAPIS -- Organization level
    @Autowired
    OrganizationQueueService organizationQueueService;

    @Autowired
    EndUserRepo endUserRepo;

    @Autowired
    RealtimeQueueRepo realtimeQueueRepo;

    @Autowired
    OrganizationQueueRepo organizationQueueRepo;

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
    public List<QueueEntity> getActiveQueues() throws Exception {
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
//    @PutMapping("/updateQueue")
//    public boolean updateQueue(String QueueId,String OrganizationId){
//        try{
//            organizationQueueService.updateQueueInfo();
//        }
//        catch(Exception databaseException){
//            //Exception scenarios
//            //1.EndUserInfo not updated
//            //2.RealTimeQueue not update but EndUserInfo updated
//            //ReasonTable not updated but above two updated
//        }
//        return true;
//    }

    @PutMapping("/updateQueueStatus")
    public boolean updateQueueStatus(@RequestParam boolean queueStatus,@RequestParam String queueId) throws Exception {

        try{
            organizationQueueService.updateQueueStatus(queueStatus,queueId);
        }
        catch(Exception databaseException){
            log.error(databaseException.getLocalizedMessage(),databaseException);
            throw new Exception("DataBase Exception");
        }
        return true;
    }
    @DeleteMapping ("/deleteQueueInfo")
    public boolean deleteQueueInfo(int queueId) throws Exception {
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
    public boolean callToken(@RequestBody Map<String,Object> queueInfo){
        try{
            //calls token as specified meaning create a real time Queue table
            RealtimeQueueInfo realtimeQueueInfo=new RealtimeQueueInfo();
            realtimeQueueInfo.setQueueId(Integer.parseInt(queueInfo.get("queueId")+""));
            //realtimeQueueInfo.setOrganizationId(Integer.parseInt(queueInfo.get("organizationId")+""));
            realtimeQueueInfo.setCurrentTokenNumber(Integer.parseInt(queueInfo.get("currenTokenNumber")+""));
            Integer tokenNumber=(endUserRepo.getHighestTokenNumber(Integer.parseInt(queueInfo.get("queueId")+"")));
            realtimeQueueInfo.setHighestTokenNumber((tokenNumber==null) ?1:Integer.parseInt(tokenNumber.toString()));
            realtimeQueueRepo.save(realtimeQueueInfo);
        }
        catch(Exception e){
            throw e;
        }
        return true;
    }

    @PostMapping("/markAsProcessed")
    public boolean markAsProcessed(@RequestBody Map<String,Object> markAsProcessedRequest){
        //should update the real time queue table
        realtimeQueueRepo.updateCurrentTokenNumber(Integer.parseInt(markAsProcessedRequest.get("queueId")+""));
        //check exception case scenarios
        return true;
    }

    //end the queue if the queue has not moved for 2hrs
    //Also consider the scenario where server crashes or the application crashes
    @PostMapping("/endQueueForCurrent")
    public boolean endQueueForCurrent(){
        try {
            //update endUserTable
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
