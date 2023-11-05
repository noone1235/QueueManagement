package com.queuemanagementsystem.Pojo;

import lombok.Data;

@Data
public class CreateTokenForQueueRequest {
    private int queueId;
    private int organizationId;
    private int user_id;
}
