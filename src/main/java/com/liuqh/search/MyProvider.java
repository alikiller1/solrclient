package com.liuqh.search;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.LeafReader;
import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.queries.CustomScoreProvider;
import org.apache.solr.common.params.SolrParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    	System.out.println(this.context.toString());
    	LeafReader reader= this.context.reader();
    	Document  docment= reader.document(doc);
    	String content1=docment.get("content1");
    	System.out.println("content1----->"+content1);
    	
    	float addScore=1;
    	String q=params.get("q");
    	String name=params.get("name");
    	System.out.println("q===="+q);
    	System.out.println("name===="+name);
    	if(content1.equals(q)){
    		addScore=4;
    	}else if(content1.startsWith(q)){
    		addScore=2;
    	}
    	/*	
         * 通过得分相乘放大分数
         * 此处可以控制与原有得分结合的方式，加减乘除都可以
         * **/

    	log.info("前台参数：params内容:{} ",params);
        log.info("查询一次：docid:{} subQueryScore:{} valSrcScore:{}",
                doc, subQueryScore,valSrcScore);
        return  subQueryScore*valSrcScore*addScore;
    }
    public static void main(String[] args) {
    	String s="q=深圳你好&defType=myparser&df=content1&indent=on&echoParams=explicit&name=123&rows=10&wt=json&_";
		
	}
}
