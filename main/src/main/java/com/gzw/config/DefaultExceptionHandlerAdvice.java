package com.gzw.config;

import com.alibaba.fastjson.JSON;
import com.gzw.domain.ResultInfo;
import com.gzw.enums.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by gujian on 2017/7/7.
 */
@RestControllerAdvice
public class DefaultExceptionHandlerAdvice {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(value = { Exception.class })
    public String exception(Exception e) {
        logger.debug(e.getMessage());
        return JSON.toJSONString(ResultInfo.getErrorInfo(ResultCode.REQUEST_ERROR.getResultCode(),e.getMessage()));
    }
}

