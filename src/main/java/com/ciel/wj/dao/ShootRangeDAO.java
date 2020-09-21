package com.ciel.wj.dao;

import com.ciel.wj.pojo.ShootRange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShootRangeDAO extends JpaRepository<ShootRange,Integer> {
    ShootRange findById(int id);
}
