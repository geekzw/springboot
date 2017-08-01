package com.gzw.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by gujian on 2017/7/27.
 */
@PropertySource("classpath:/https.properties")
@ConfigurationProperties(prefix = "comment.https")
@Component
@Data
public class MessageModel {

    @Value("${JSMS.appKey}")
    private String appKey;

    @Value("${JSMS.Secret}")
    private String secret;

    @Value("${JSMS.mobile}")
    private String mobile;

    @Value("${JSMS.url}")
    private String url;

    @Value("${JSMS.modelId}")
    private Integer temp_id;

    @Value("${JSMS.content}")
    private String content;

    private Map<String,String> temp_para;
}
