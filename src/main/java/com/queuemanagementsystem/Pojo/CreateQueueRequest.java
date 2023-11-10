package com.queuemanagementsystem.Pojo;

import lombok.Data;

@Data
public class CreateQueueRequest extends QueueInfo {

    public CreateQueueRequest(){
        super();
    }

    public int organizationId;
    private String tokenGroup;
    private String tokenSubGroup;

}
