package com.liuqh.similarity;

import org.apache.lucene.search.similarities.BasicStats;
import org.apache.lucene.search.similarities.SimilarityBase;

public class MySimilarity extends SimilarityBase {

	@Override
	protected float score(BasicStats stats, float freq, float docLen) {
		return 1.0f;
	}

	@Override
	public String toString() {
		return null;
	}


}
