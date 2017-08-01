package com.gzw.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by gujian on 2017/7/27.
 */
public class HttpClientUtil {

    public static String Post(String url,String data,Map<String,String> header){
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        if(data!=null){

            try {
                StringEntity entity = new StringEntity(data);
                entity.setContentEncoding("UTF-8");
                post.setEntity(entity);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        if(header!=null){
            for(Map.Entry<String,String> entry:header.entrySet()){
                post.setHeader(entry.getKey(),entry.getValue());
            }
        }
        try {
            CloseableHttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            InputStream inputStream = entity.getContent();
            Scanner scanner = new Scanner(inputStream);
            String content = scanner.next();
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
