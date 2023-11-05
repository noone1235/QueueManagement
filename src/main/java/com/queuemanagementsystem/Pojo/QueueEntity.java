package com.queuemanagementsystem.Pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="organizationqueue",schema="public")
public class QueueEntity extends QueueInfo {
    @Id
    @JsonProperty("queue_id")
    @Column(name = "queue_id")
    public int queueId;

    @JsonProperty("oraganization_id")
    @Column(name = "oraganization_id")
    public int organizationId;

    @JsonProperty("queue_status")
    @Column(name="queue_status")
    public boolean queueStatus;

}
