package com.queuemanagementsystem.Pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="sub_group",schema="public")
public class SubGroupInfo {

    @Column(name="category_id")
    public int groupId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sub_group_id")
    public int sub_group_id;

    @Column(name="subGroupName")
    public String subGroupName;
}
