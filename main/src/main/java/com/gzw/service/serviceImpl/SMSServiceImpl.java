package com.gzw.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.gzw.domain.MessageModel;
import com.gzw.service.SMSService;
import com.gzw.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gujian on 2017/7/27.
 */
@Service

public class SMSServiceImpl implements SMSService {

    @Autowired
    private MessageModel messageModel;

    @Override
    public String sendSMS(Map<String, String> data) {
        String se = messageModel.getAppKey()+":"+messageModel.getSecret();
        String mm = "Basic "+new String(Base64Utils.encode(se.getBytes()));

        Map<String,String> headers = new HashMap<>();
        Map<String,String> dd = new HashMap<>();
        dd.put("code",messageModel.getContent());

        messageModel.setTemp_para(dd);

        headers.put("Authorization",mm);
        headers.put("Content-Type","application/json");
        HttpClientUtil.Post(messageModel.getUrl(),JSON.toJSONString(messageModel),headers);

        return null;
    }
}
