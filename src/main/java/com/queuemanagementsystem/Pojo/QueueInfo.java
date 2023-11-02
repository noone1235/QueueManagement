package com.queuemanagementsystem.Pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="",schema="")
public class QueueInfo {

    @Id
    @Column(name="queue_id")
    public String queueId;

    @Column(name="organization_id")
    public String organizationId;

    @Column(name="current_token_number")
    public String currentTokenNumber;
}
