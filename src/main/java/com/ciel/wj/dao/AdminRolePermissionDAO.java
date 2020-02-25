package com.ciel.wj.dao;

import com.ciel.wj.pojo.AdminRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRolePermissionDAO extends JpaRepository<AdminRolePermission,Integer> {
    List<AdminRolePermission> findAllByRid(int rid);
    void deleteAllByRid(int rid);
}
