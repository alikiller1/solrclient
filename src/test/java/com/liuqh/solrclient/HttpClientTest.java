package com.liuqh.solrclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.junit.Test;

public class HttpClientTest {
	@Test
	public void test1(){
				String result = "";
				try {
					URL httpurl = new URL("http://localhost:8483/solr/core2/select");
					HttpURLConnection httpConn = (HttpURLConnection) httpurl.openConnection();
					httpConn.setRequestMethod("POST");
					httpConn.setDoOutput(true);
					httpConn.setDoInput(true);
					httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
					PrintWriter out = new PrintWriter(httpConn.getOutputStream());
					out.write("df=searchText&indent=ture&wt=json&q="+URLEncoder.encode("胖", "UTF-8"));
					out.flush();
					
					
					BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
					String line;
					while ((line = in.readLine()) != null) {
						result += line+"\r\n";
					}
					System.out.println(result);
					in.close();
					out.close();
					
				} catch (Exception e) {
					System.out.println("没有结果！" + e);
				}
	}
}
