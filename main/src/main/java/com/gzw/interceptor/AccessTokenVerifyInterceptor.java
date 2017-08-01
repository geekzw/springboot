package com.gzw.interceptor;

import com.alibaba.fastjson.JSON;
import com.gzw.domain.ResultInfo;
import com.gzw.domain.Token;
import com.gzw.enums.ResultCode;
import com.gzw.service.RedisTokenService;
import com.gzw.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gujian on 2017/7/7.
 */

@Configuration
public class AccessTokenVerifyInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTokenService service;

    private final static Logger LOG = LoggerFactory.getLogger(AccessTokenVerifyInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        LOG.info("AccessToken executing ...");
        boolean flag = false;

//        if (service == null) {//解决service为null无法注入问题
//            System.out.println("operatorLogService is null!!!");
//            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
//            service = (RedisTokenServiceImpl) factory.getBean("redisTokenServiceImpl");
//        }

        // token
        String accessToken = request.getParameter("token");
        if (StringUtil.isNotEmpty(accessToken)) {
            // 验证
            Token token = service.getToken(accessToken);
            flag = service.checkToken(token);
        }

        if (!flag) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
//            response.getWriter().print("AccessToken ERROR");
            response.getWriter().write(JSON.toJSONString(ResultInfo.getErrorInfo(ResultCode.LOGIN_ERROR)));
        }

        return flag;
    }
}
