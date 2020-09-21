package com.ciel.wj.dao;

import com.ciel.wj.pojo.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountDAO extends JpaRepository<Discount,Integer> {
    Discount findById(int id);
}
