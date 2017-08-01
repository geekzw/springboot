package com.gzw.service.serviceImpl;

import com.gzw.scheduled.SendEmailJob;
import com.gzw.service.ScheduledService;
import com.gzw.utils.ScheduleUtil;
import org.springframework.stereotype.Service;

/**
 * Created by gujian on 2017/7/24.
 */
@Service
public class ScheduledServiceImpl implements ScheduledService {

    @Override
    public void exeJob(String time, String type) {
        if(type.equals("email")){
            ScheduleUtil.exe(time, SendEmailJob.class);
        }

    }
}
