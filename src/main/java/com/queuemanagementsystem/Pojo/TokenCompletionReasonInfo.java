package com.queuemanagementsystem.Pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="reasontable",schema="public")
public class TokenCompletionReasonInfo {

    @Id
    @Column(name="reason_id")
    public String reasonId;

    @Column(name="token_number")
    public String token_number;

    @Column(name="queue_id")
    public String queueId;
}
