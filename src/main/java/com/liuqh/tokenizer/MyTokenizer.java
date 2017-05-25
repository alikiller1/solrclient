package com.liuqh.tokenizer;

import org.apache.lucene.analysis.util.CharTokenizer;
import org.apache.lucene.util.AttributeFactory;

public class MyTokenizer extends CharTokenizer {

	public MyTokenizer() {

	}

	public MyTokenizer(AttributeFactory factory) {
		super(factory);
	}

	/**
	 * Collects only characters which do not satisfy 参数c指的是term的ASCII值，竖线的值为 124
	 */
	@Override
	protected boolean isTokenChar(int c) {
		return !(c == 124);
	}
	
	public static void main(String[] args) {
		char c='|';
		System.out.println(c==124);
	}

}
