package com.ciel.wj.service;

import com.ciel.wj.dao.JotterArticleDAO;
import com.ciel.wj.pojo.JotterArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class JotterArticleService {
    @Autowired
    JotterArticleDAO jotterArticleDAO;

    public Page list(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return jotterArticleDAO.findAll(PageRequest.of(page, size, sort));
    }

    public JotterArticle findById(int id) {
        return jotterArticleDAO.findById(id);
    }

    public void addOrUpdate(JotterArticle article) {
        jotterArticleDAO.save(article);
    }

    public void delete(int id) {
        jotterArticleDAO.deleteById(id);
    }
}
