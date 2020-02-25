package com.ciel.wj.dao;

import com.ciel.wj.pojo.AdminPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminPermissionDAO extends JpaRepository<AdminPermission,Integer> {
    AdminPermission findById(int id);
}
