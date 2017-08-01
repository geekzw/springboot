package com.gzw.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Created by gujian on 2017/7/24.
 */
public class JsoupUtil {

    public static String getWheather(String url){

        try {
            Document document = Jsoup.connect(url).get();
            Element element = document.getElementById("7d");
            element.getElementsByTag("ul");
            element = element.getElementsByTag("ul").get(0);
            return element.getElementsByTag("li").html();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
