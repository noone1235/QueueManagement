package com.queuemanagementsystem.Pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.sql.Timestamp;

@Data
@MappedSuperclass
public class QueueInfo {

    @JsonProperty("token_type")
    @Column(name = "token_type")
    public String tokenType;

    @JsonProperty("queue_name")
    @Column(name = "queue_name")
    public String queueName;

    @JsonProperty("queue_size")
    @Column(name = "queue_size")
    public int queueSize;

    @JsonProperty("queue_start_time")
    @Column(name = "queue_start_time")
    public Timestamp queueStartTime;

    @JsonProperty("queue_end_time")
    @Column(name = "queue_end_time")
    public Timestamp queueEndTime;

    @JsonProperty("queue_frequency")
    @Column(name = "queue_frequency")
    public int queueFrequency;

    @JsonProperty("token_reset")
    @Column(name = "token_reset")
    public String tokenReset;

    @JsonProperty("created_by")
    @Column(name = "created_by")
    public String createdBy;

    @JsonProperty("created_date")
    @Column(name = "created_date")
    public Timestamp createdDate;

    @JsonProperty("modified_by")
    @Column(name = "modified_by")
    public String modifiedBy;

    @JsonProperty("modified_date")
    @Column(name = "modified_date")
    public Timestamp modifiedDate;
}
