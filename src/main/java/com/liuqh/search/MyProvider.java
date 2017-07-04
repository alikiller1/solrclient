package com.liuqh.search;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.LeafReader;
import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.index.SortedDocValues;
import org.apache.lucene.queries.CustomScoreProvider;
import org.apache.solr.common.params.SolrParams;
import org.eclipse.jetty.util.UrlEncoded;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ctc.wstx.util.StringUtil;

/**
 * Created by qindongliang on 2016/5/9.
 */
public class MyProvider extends CustomScoreProvider {

    private SolrParams params;
    final static Logger log= LoggerFactory.getLogger(MyProvider.class);
    //配置参数
    public void setParams(SolrParams params) {
        this.params = params;
    }

    public MyProvider(LeafReaderContext context) {
        super(context);
    }

    @Override
    public float customScore(int doc, float subQueryScore, float valSrcScore) throws IOException {
    	long allUseTime;
    	long startTime=System.currentTimeMillis();
    	long getDocTime;
    	String mode=params.get("mode");
    	if(StringUtils.isNotBlank(mode)&&"1".equals(mode)){
    		   allUseTime=System.currentTimeMillis()-startTime;
    		   if(allUseTime>0){
    	        	log.info("all time="+(System.currentTimeMillis()-startTime));
    	        }
    		return subQueryScore*valSrcScore;
    	}else if(StringUtils.isNotBlank(mode)&&"2".equals(mode)){
    		LeafReader reader= this.context.reader();
        	Document  docment= reader.document(doc);
        	 allUseTime=System.currentTimeMillis()-startTime;
  		   if(allUseTime>0){
  	        	log.info("all time="+(System.currentTimeMillis()-startTime));
  	        }
        	return subQueryScore*valSrcScore;
    	}else if(StringUtils.isNotBlank(mode)&&"3".equals(mode)){
    		LeafReader reader= this.context.reader();
        	Document  docment= reader.document(doc);
        	String content1=docment.get("content1");
        	String name=docment.get("name");
        	String age=docment.get("age");
        	allUseTime=System.currentTimeMillis()-startTime;
  		   if(allUseTime>0){
  	        	log.info("all time="+(System.currentTimeMillis()-startTime));
  	        }
        	return subQueryScore*valSrcScore;
    	}else{
    		LeafReader reader= this.context.reader();
    		reader.getRefCount();
        	Document  docment= reader.document(doc);
        	String content1=docment.get("content1");
        	String name=docment.get("name");
        	String age=docment.get("age");
        	getDocTime=System.currentTimeMillis()-startTime;
        	if(getDocTime>0){
        		log.info("get document use time="+getDocTime);
        	}
        	String queryStr=params.get("queryStr");
        	queryStr=URLDecoder.decode(queryStr, "UFT-8");
        	float score=1;
        	if(content1.equals(queryStr)){
        		score=4;
        	}else{
        		if(content1.startsWith(queryStr)){
        			score=3;
        		}else{
        			score=0;
        		}
        	}
    		//log.info("查询一次：docid:{} content1:{} queryStr:{}", doc, content1, queryStr);
             allUseTime=System.currentTimeMillis()-startTime;
            if(allUseTime>0){
            	log.info("all time="+(System.currentTimeMillis()-startTime));
            }
            return  score;
    	}
    	
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
    	String s="深圳+拍拍";
    	System.out.println(URLDecoder.decode(s,"UFT-8"));
		
	}
}
