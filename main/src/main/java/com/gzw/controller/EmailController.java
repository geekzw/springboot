package com.gzw.controller;

import com.alibaba.fastjson.JSON;
import com.gzw.service.serviceImpl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gujian on 2017/6/26.
 */
@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/sendSimpleEmail")
    public String sendSimpleEmail(){
        return JSON.toJSONString(emailService.sendSimpleEmail());
    }

    @PostMapping(value = "/sendFileMail")
    public String sendFileMail(){
        return JSON.toJSONString(emailService.sendFileMail());
    }
}
