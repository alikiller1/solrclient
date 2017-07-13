package com.liuqh.solrclient.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import com.liuqh.solrclient.HttpClientUtil;
import com.liuqh.solrclient.HttpRequest;

public class QueryService {

	public static void queryByHttpPool(String queryWord) {
		String baseUrl = "http://localhost:8483/solr/core2/select?df=searchText&indent=ture&wt=json&q=";
		String queryUrl = baseUrl + URLEncoder.encode(queryWord);
		String resp = HttpClientUtil.get(queryUrl);
		// System.out.println("resp-->"+resp);
	}

	public static void queryByOneSolrjClient(String queryWord, int queryTimes) throws SolrServerException, IOException {
		int count=0;
		String serverUrl = "http://localhost:8483/solr/core2";
		HttpSolrClient solrClient = new HttpSolrClient.Builder(serverUrl).build();
		SolrQuery sq = new SolrQuery();
		sq.set("q", queryWord);
		sq.set("df", "searchText");
		sq.setStart(0);
		sq.setRows(10);
		for (int i = 0; i < queryTimes; i++) {
			count++;
			QueryResponse result = solrClient.query(sq);
			 SolrDocumentList results = result.getResults();
			//System.out.println(results.getNumFound());
		}
		System.out.println("count="+count);
		solrClient.commit();
		solrClient.close();

	}
	
	public static void queryByNewSolrjEveryTime(String queryWord) throws SolrServerException, IOException{
		String serverUrl = "http://localhost:8483/solr/core2";
		HttpSolrClient solrClient = new HttpSolrClient.Builder(serverUrl).build();
		SolrQuery sq = new SolrQuery();
		sq.set("q", queryWord);
		sq.set("df", "searchText");
		sq.setStart(0);
		sq.setRows(10);
		QueryResponse result = solrClient.query(sq);
		SolrDocumentList results = result.getResults();
	   // System.out.println(results.getNumFound());
		solrClient.commit();
		solrClient.close();
	}
	
	public static void queryByNewHttpRequeryEveryTime(String queryWord) throws UnsupportedEncodingException{

		String baseUrl = "http://localhost:8483/solr/core2/select?df=searchText&indent=ture&wt=json&q=";
		String queryUrl = baseUrl + URLEncoder.encode(queryWord,"UTF-8");
		String resp=HttpRequest.sendGet(queryUrl, null);
		//System.out.println("resp->"+resp);
	}

	public static void main(String[] args) throws SolrServerException, IOException {
		queryByHttpPool("矮");
		queryByOneSolrjClient("矮",1);
		queryByNewHttpRequeryEveryTime("矮");
		queryByNewSolrjEveryTime("矮");
	}
}
