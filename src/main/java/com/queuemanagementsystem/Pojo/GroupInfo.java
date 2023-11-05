package com.queuemanagementsystem.Pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "", schema = "")
public class GroupInfo {

    @Id
    @Column(name = "group_id")
    private int groupId;

    @Column(name = "organization_id")
    private int organizationId;
}
