package com.queuemanagementsystem.Pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;


@Entity
@Data
@Table(name = "end_user", schema = "public")
public class EndUserInfo {
    @Id
    @Column(name = "user_id")
    private int userId;

    @Column(name = "queue_id")
    private int queueId;

    @Column(name = "token_number")
    private int tokenNumber;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_date")
    private Timestamp modifiedDate;
}
