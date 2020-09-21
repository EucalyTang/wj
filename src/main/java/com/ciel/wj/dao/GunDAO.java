package com.ciel.wj.dao;

import com.ciel.wj.pojo.Gun;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GunDAO extends JpaRepository<Gun,Integer> {
    Gun findById(int id);
}
