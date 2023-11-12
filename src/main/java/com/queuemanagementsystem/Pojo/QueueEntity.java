package com.queuemanagementsystem.Pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@Table(name="organization_queue",schema="public")
public class QueueEntity extends QueueInfo {


    public QueueEntity(){
        super();
    }

    @Id
    @Column(name = "queue_id")
    public Integer queueId;

    @Column(name = "organization_id")
    public int organizationId;

    @Column(name = "group_id")
    public int groupId;

    @Column(name = "sub_group_id")
    public int subGroupId;

    @Column(name="queue_is_active")
    public boolean queueStatus;

}
