package com.gzw.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.gzw.domain.Article;
import com.gzw.domain.ArticleExample;
import com.gzw.domain.ResultInfo;
import com.gzw.domain.User;
import com.gzw.enums.ResultCode;
import com.gzw.mappers.ArticleMapper;
import com.gzw.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by gujian on 2017/6/23.
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    public ResultInfo getArticleByAuthor(String nickName,Integer pageSize,Integer pageStart){
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andAuthorEqualTo(nickName);
        List<Article> list;
        ResultInfo resultInfo;
        ValueOperations<String, List<Article>> operations = redisTemplate.opsForValue();
        String key = nickName+pageSize+pageStart;
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            list = operations.get(key);
            resultInfo =  ResultInfo.getSuccessWithInfo(ResultCode.RESULT_SUCCESS,list);
            return resultInfo;
        }
        PageHelper.startPage(pageStart,pageSize);

        list = articleMapper.selectByExample(articleExample);
        if(list == null || list.size()<=0){
            resultInfo = ResultInfo.getErrorInfo(ResultCode.RESULT_EMPTY);
        }else{
            operations.set(key,list,20, TimeUnit.SECONDS);
            resultInfo =  ResultInfo.getSuccessWithInfo(ResultCode.RESULT_SUCCESS,list);
        }
        return resultInfo;
    }

    public ResultInfo writeArticle(Article article, HttpSession session){
        ResultInfo resultInfo;
        if(StringUtil.isEmpty(article.getTitle())){
            resultInfo = ResultInfo.getErrorInfo(ResultCode.TITLE_EMPTY);
        }else if(StringUtil.isEmpty(article.getContent())){
            resultInfo = ResultInfo.getErrorInfo(ResultCode.CONENT_EMPTY);
        }else{
            User user = (User) session.getAttribute("user");
            article.setUsername(user.getUsername());
            articleMapper.insert(article);
            resultInfo = ResultInfo.getSuccessInfo(ResultCode.RESULT_SUCCESS);
        }
        return resultInfo;
    }
}
