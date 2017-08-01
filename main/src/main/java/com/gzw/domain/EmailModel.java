package com.gzw.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by gujian on 2017/7/24.
 */
@PropertySource("classpath:/comment.properties")
@ConfigurationProperties(prefix = "custom.tomcat.https")
@Component
@Data
public class EmailModel {

    @Value("${email.to}")
    private String emailTo;

    @Value("${email.title}")
    private String emailTitle;

    @Value("${email.content}")
    private String emailContent;

}
