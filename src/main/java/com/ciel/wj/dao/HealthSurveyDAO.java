package com.ciel.wj.dao;

import com.ciel.wj.pojo.HealthSurvey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthSurveyDAO extends JpaRepository<HealthSurvey, Integer> {
    HealthSurvey findById(int id);
}
