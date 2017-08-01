package com.gzw.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gzw.service.HelloService;

/**
 * Created by gujian on 2017/7/29.
 */
@Service(version="1.0")
public class HelloServiceImpl implements HelloService {
    @Override
    public String getHi() {
        return "hello";
    }
}
