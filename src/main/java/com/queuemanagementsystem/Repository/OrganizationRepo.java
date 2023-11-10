package com.queuemanagementsystem.Repository;

import com.queuemanagementsystem.Pojo.OrganizationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepo extends JpaRepository<OrganizationInfo,Integer> {
    OrganizationInfo findByOrganizationName(String organizationName);
}
