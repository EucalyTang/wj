package com.ciel.wj.dao;

import com.ciel.wj.pojo.JotterArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JotterArticleDAO extends JpaRepository<JotterArticle,Integer> {
    JotterArticle findById(int id);
}
