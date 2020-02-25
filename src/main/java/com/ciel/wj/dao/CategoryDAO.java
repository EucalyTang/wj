package com.ciel.wj.dao;

import com.ciel.wj.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category, Integer> {

}
