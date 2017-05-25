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
		//float avgFieldLength=stats.getAvgFieldLength();
		return 0;
	}
	
	


}
