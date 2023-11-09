package com.queuemanagementsystem.Pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name="organization_info",schema="public")
public class OrganizationInfo {
    @Column(name="organization_id")
    public int organizationId;

    @Column(name="organization_name")
    public String OrganizationName;
}
