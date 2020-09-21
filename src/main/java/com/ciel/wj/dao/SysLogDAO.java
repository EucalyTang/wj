package com.ciel.wj.dao;

import com.ciel.wj.pojo.SysLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysLogDAO extends JpaRepository<SysLog,Integer> {
    SysLog findById(int id);
    @Query(
            value = "SELECT * FROM sys_log WHERE user_id = ?1",
            countQuery = "SELECT COUNT(1) FROM sys_log WHERE user_id = ?1",
            nativeQuery = true
    )
    Page<SysLog> findByUserId(int uid, Pageable pageable);
}
