package com.ciel.wj.controller;

import com.ciel.wj.pojo.HealthSurvey;
import com.ciel.wj.service.HealthSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SurveyController {
    @Autowired
    HealthSurveyService healthSurveyService;

    @GetMapping("api/survey/health/{size}/{page}")
    public Page listHealth(@PathVariable("size") int size, @PathVariable("page") int page) {
        return healthSurveyService.list(page-1, size);
    }

    @GetMapping("api/survey/health/{id}")
    public HealthSurvey getOneHealth(@PathVariable("id") int id) {
        return healthSurveyService.findById(id);
    }
}
