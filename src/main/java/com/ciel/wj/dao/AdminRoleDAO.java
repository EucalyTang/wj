package com.ciel.wj.dao;

import com.ciel.wj.pojo.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRoleDAO extends JpaRepository<AdminRole,Integer> {
    AdminRole findById(int id);
}
