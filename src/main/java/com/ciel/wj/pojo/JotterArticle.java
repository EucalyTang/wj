package com.ciel.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "jotter_article")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class JotterArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String articleTitle;
    String articleContentHtml;
    String articleContentMd;
    String articleAbstract;
    String articleCover;
    Date articleDate;

    public void setArticleContentMd(String articleContentMd) {
        this.articleContentMd = articleContentMd;
    }

    public String getArticleContentMd() {
        return articleContentMd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public void setArticleContentHtml(String articleContentHtml) {
        this.articleContentHtml = articleContentHtml;
    }

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    public void setArticleCover(String articleCover) {
        this.articleCover = articleCover;
    }

    public void setArticleDate(Date articleDate) {
        this.articleDate = articleDate;
    }

    public int getId() {
        return id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getArticleContentHtml() {
        return articleContentHtml;
    }

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public String getArticleCover() {
        return articleCover;
    }

    public Date getArticleDate() {
        return articleDate;
    }
}
