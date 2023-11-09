package com.queuemanagementsystem.Pojo;

import lombok.Data;

@Data
public class CreateQueueRequest extends QueueInfo {

    public CreateQueueRequest(){
        super();
    }

    private String tokenGroup;
    private String tokenSubGroup;

}
