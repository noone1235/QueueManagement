package com.queuemanagementsystem.Pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "real_time_queue", schema = "public")
public class RealtimeQueueInfo {

    @Id
    @Column(name = "queue_id")
    public int queueId;

    @Column(name = "current_token_number")
    public int currentTokenNumber;

    @Column(name="highest_token_number")
    public int highestTokenNumber;
}
