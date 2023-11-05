package com.queuemanagementsystem.Repository;

import com.queuemanagementsystem.Pojo.GroupInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepo extends JpaRepository<GroupInfo,Integer> {
}
