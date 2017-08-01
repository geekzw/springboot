package com.gzw.service.serviceImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gzw.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Created by gujian on 2017/7/29.
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Reference(version = "1.0")
    HelloService helloService;

    @Override
    public String getHi() {
        return helloService.getHi();
    }
}
