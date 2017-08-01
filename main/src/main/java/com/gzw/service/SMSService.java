package com.gzw.service;

import java.util.Map;

/**
 * Created by gujian on 2017/7/27.
 */
public interface SMSService {

    String sendSMS(Map<String, String> data);
}
