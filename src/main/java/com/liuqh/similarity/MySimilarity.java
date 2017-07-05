package com.liuqh.similarity;

import org.apache.lucene.search.similarities.BasicStats;
import org.apache.lucene.search.similarities.SimilarityBase;

public class MySimilarity extends SimilarityBase {

	

	@Override
	public String toString() {
		System.out.println("---->MySimilarity");
		return null;
	}

	@Override
	protected float score(BasicStats stats, float freq, float docLen) {
//		getAvgFieldLength-->14.0平均半场字段长，
//		getBoost-->1.0
//		getValueForNormalization-->1.0
//		getDocFreq-->3.0在几个文档出现了
//		getNumberOfDocuments-->9.0文档个数
//		getNumberOfFieldTokens-->126.0 所有文档总字段长
//		getTotalTermFreq-->9.0总共的出现次数
//		freq-->6.0在这个文档出现了几次
//		docLen-->28.444445文档的长度
		System.out.println("getDiscountOverlaps-->"+this.getDiscountOverlaps());
		float avgFieldLength=stats.getAvgFieldLength();
		float boost=stats.getBoost();
		float normalization=stats.getValueForNormalization();
		float docFreq=stats.getDocFreq();
		float numberOfDocuments=stats.getNumberOfDocuments();
		float numberOfFieldTokens=stats.getNumberOfFieldTokens();
		float totalTermFreq=stats.getTotalTermFreq();
		System.out.println("AvgFieldLength-->"+avgFieldLength);
		System.out.println("getBoost-->"+boost);
		System.out.println("getValueForNormalization-->"+normalization);
		System.out.println("getDocFreq-->"+docFreq);
		System.out.println("getNumberOfDocuments-->"+numberOfDocuments);
		System.out.println("getNumberOfFieldTokens-->"+numberOfFieldTokens);
		System.out.println("getTotalTermFreq-->"+totalTermFreq);
		System.out.println("freq-->"+freq);
		System.out.println("docLen-->"+docLen);
		freq=1;
		float a=freq/docLen;
		return a;
		
	}
	
	public static void main(String[] args) {
		System.out.println(Math.log(5));
	}
	


}
