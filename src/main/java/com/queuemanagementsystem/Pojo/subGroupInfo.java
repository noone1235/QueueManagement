package com.queuemanagementsystem.Pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="",schema="")
public class subGroupInfo {

    @Column(name="category_id")
    public int categoryId;

    @Id
    @Column(name="sub_group_id")
    public int sub_group_id;
}
