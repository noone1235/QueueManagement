package com.queuemanagementsystem.Repository;

import com.queuemanagementsystem.Pojo.UserOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrganizationRepo extends JpaRepository<UserOrganization,Integer> {
}
