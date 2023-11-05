package com.queuemanagementsystem.Pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="",schema="")
public class QueueEntity extends QueueInfo {
    @Id
    @Column(name = "queue_id")
    public int queueId;

    @Column(name = "oraganization_id")
    public int organizationId;

    @Column(name="queue_status")
    public boolean queueStatus;

}
