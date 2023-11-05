package com.queuemanagementsystem.Pojo;

import lombok.Data;

@Data
public class CreateQueueRequest extends QueueInfo {
    private String tokenGroup;
    private String tokenSubGroup;
}
