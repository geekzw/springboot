package com.gzw.controller;

import com.alibaba.fastjson.JSON;
import com.gzw.domain.Article;
import com.gzw.domain.ResultInfo;
import com.gzw.enums.ResultCode;
import com.gzw.service.serviceImpl.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by gujian on 2017/6/23.
 */
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping(value = "/article")
    public String getArticleByAuthor(@RequestParam Integer pageSize,@RequestParam Integer pageStart,@RequestParam String name){
        if(pageSize!=null && pageStart!=null){
            return JSON.toJSONString(articleService.getArticleByAuthor(name,pageSize,pageStart));
        }

        return JSON.toJSONString( ResultInfo.getErrorInfo(ResultCode.RESULT_EMPTY));

    }

    @PostMapping(value = "/do_write")
    public String writeArticle(Article article, HttpSession session) {

        return JSON.toJSONString(articleService.writeArticle(article, session));
    }
}
