package com.ciel.wj.controller;

import com.ciel.wj.pojo.JotterArticle;
import com.ciel.wj.result.Result;
import com.ciel.wj.result.ResultFactory;
import com.ciel.wj.service.JotterArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
public class JotterController {
    @Autowired
    JotterArticleService jotterArticleService;

    @PostMapping("api/admin/content/article")
    public Result saveArticle(@RequestBody JotterArticle article) {
        jotterArticleService.addOrUpdate(article);
        return ResultFactory.buildSuccessResult("保存成功");
    }

    @GetMapping("api/article/{size}/{page}")
    public Page listArticles(@PathVariable("size") int size, @PathVariable("page") int page) {
        return jotterArticleService.list(page-1, size);
    }

    @GetMapping("api/article/{id}")
    public JotterArticle getOneArticle(@PathVariable("id") int id) {
        return jotterArticleService.findById(id);
    }

    @DeleteMapping("api/admin/content/article/{id}")
    public Result deleteArticle(@PathVariable("id") int id) {
        jotterArticleService.delete(id);
        return ResultFactory.buildSuccessResult("删除成功");
    }
}
