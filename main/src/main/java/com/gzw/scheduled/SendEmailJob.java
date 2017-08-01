package com.gzw.scheduled;

import com.gzw.SpringContext;
import com.gzw.service.serviceImpl.EmailService;
import com.gzw.utils.JsoupUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by gujian on 2017/7/24.
 */
public class SendEmailJob implements Job {

    @Autowired
    private EmailService service;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        if (service == null) {//解决service为null无法注入问题
            System.out.println("operatorLogService is null!!!");
            service = SpringContext.getBean(EmailService.class);
        }

        service.sendSimpleEmail("天气预报", JsoupUtil.getWheather("http://www.weather.com.cn/weather/101210101.shtml"));
    }
}
