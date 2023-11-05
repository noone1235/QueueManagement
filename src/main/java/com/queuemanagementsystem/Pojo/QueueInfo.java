package com.queuemanagementsystem.Pojo;

import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class QueueInfo {

    @Column(name = "token_type")
    public String tokenType;

    @Column(name = "queue_name")
    public String queueName;

    @Column(name = "queue_size")
    public int queueSize;

    @Column(name = "queue_start_time")
    public Timestamp queueStartTime;

    @Column(name = "queue_end_time")
    public Timestamp queueEndTime;

    @Column(name = "queue_frequency")
    public String queueFrequency;

    @Column(name = "token_reset")
    public String tokenReset;

    @Column(name = "created_by")
    public String createdBy;

    @Column(name = "created_date")
    public Timestamp createdDate;

    @Column(name = "modified_by")
    public String modifiedBy;

    @Column(name = "modified_date")
    public Timestamp modifiedDate;
}
