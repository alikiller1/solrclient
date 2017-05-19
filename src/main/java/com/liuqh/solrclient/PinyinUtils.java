package com.liuqh.solrclient;

import org.nlpcn.commons.lang.pinyin.Pinyin;

public class PinyinUtils {
	public static void main(String[] args) {
		System.out.println(Pinyin.pinyin("中国"));
		System.out.println(Pinyin.tonePinyin("中国"));
		System.out.println(Pinyin.unicodePinyin("中国"));
	}
}
