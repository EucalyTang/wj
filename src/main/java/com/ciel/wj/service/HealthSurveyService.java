package com.ciel.wj.service;

import com.ciel.wj.dao.HealthSurveyDAO;
import com.ciel.wj.pojo.HealthSurvey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class HealthSurveyService {
    @Autowired
    HealthSurveyDAO healthSurveyDAO;

    public Page list(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return healthSurveyDAO.findAll(PageRequest.of(page, size, sort));
    }

    public HealthSurvey findById(int id) {
        return healthSurveyDAO.findById(id);
    }

}
