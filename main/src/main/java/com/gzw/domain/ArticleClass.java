package com.gzw.domain;

import java.io.Serializable;

/**
 * Created by gujian on 2017/6/30.
 */
public class ArticleClass implements Serializable{

    private int id;

    private String categoryName;

    private int articleCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }
}
