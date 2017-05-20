package com.liuqh.solrclient;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Test;

import com.liuqh.solrclient.service.QueryService;

public class SearchServiceTest {
	
	@Test
	public void test1() throws SolrServerException, IOException{
		int times=3;
		String queryWord="高 矮 深圳 福";
		
		/*--------------------------*/
		long startTime2=System.currentTimeMillis();
		QueryService.queryByOneSolrjClient(queryWord, times);
		long endTime2=System.currentTimeMillis();
		System.out.println("queryByOneSolrjClient耗时（毫秒）="+(endTime2-startTime2));
		/*--------------------------*/
	
		long startTime1=System.currentTimeMillis();
		for(int i=0;i<times;i++){
			QueryService.queryByHttpPool(queryWord);
		}
		long endTime1=System.currentTimeMillis();
		System.out.println("queryByHttpPool耗时（毫秒）="+(endTime1-startTime1));
		/*--------------------------*/
		
		long startTime3=System.currentTimeMillis();
		for(int i=0;i<times;i++){
			QueryService.queryByNewHttpRequeryEveryTime(queryWord);
		}
		long endTime3=System.currentTimeMillis();
		System.out.println("queryByNewHttpRequeryEveryTime耗时（毫秒）="+(endTime3-startTime3));
		/*--------------------------*/
		long startTime4=System.currentTimeMillis();
		for(int i=0;i<times;i++){
			QueryService.queryByNewSolrjEveryTime(queryWord);
		}
		long endTime4=System.currentTimeMillis();
		System.out.println("queryByNewSolrjEveryTime耗时（毫秒）="+(endTime4-startTime4));
	}
}
