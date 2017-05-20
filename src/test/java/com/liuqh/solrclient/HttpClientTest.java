package com.liuqh.solrclient;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.junit.Test;

public class HttpClientTest {
	@Test
	public void test1(){
		HttpGet httpGet=new HttpGet("http://mapi.paicaifu.com");
		 httpGet.setHeader("User-Agent", "Mozilla/5.0");
         httpGet.setHeader("Accept",
         "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
         httpGet.setHeader("Accept-Language",
         "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
         httpGet.setHeader("Accept-Charset",
         "ISO-8859-1,utf-8,gbk,gb2312;q=0.7,*;q=0.7");

        // 配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(10000)
                .setConnectTimeout(10000).setSocketTimeout(10000).build();
        httpGet.setConfig(requestConfig);
        
	}
}
