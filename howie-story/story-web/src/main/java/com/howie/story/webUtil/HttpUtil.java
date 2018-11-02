package com.howie.story.webUtil;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 上午10:27 2018/10/10
 * @Modified by:xiaohaoyun
 */
public class HttpUtil {

    static final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {
        String result = httpGet();
        System.out.println("result: "+result);
    }

    public static String httpGet () {
        String url = "http://cat.vip.sankuai.com/cat/r/q?startDate=201809220100&endDate=201809221759&domain=idp-config-service&ip=All&reportName=event&params=[{\"type\":\"SQL.Method\",\"names\":[\"SELECT\",\"INSERT\"]},{\"type\":\"URL\",\"names\":[\"*\"]}]&op=queryData&token=7cf59ab23e064c2d81f896b1569480f8";
        String apiUrl = "http://cat.vip.sankuai.com/cat/r/q?startDate=201809220100&endDate=201809221759&domain=idp-config-service&reportName=api&params=[{\"type\":\"SQL.Method\",\"names\":[\"SELECT\",\"INSERT\"]},{\"type\":\"URL\",\"names\":[\"*\"]}]&op=queryData&token=7cf59ab23e064c2d81f896b1569480f8";
        Request request =  new Request.Builder().url(url).build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            System.out.println("出现异常");
            e.printStackTrace();

        }


        return "";
    }

}
