package com.liuqh.solrclient;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @author :liuqinghua
 * @version 创建时间：2017年7月31日 下午5:49:34
 * 
 */
public class GeneCharTest {
	public static void main(String[] args) {
		for(int i=0;i<100;i++){
			System.out.println(getRodomQueryStr());
		}
	}
	public static String getRodomQueryStr(){
		Random random = new Random();
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < 10; i++) {
			char[] s = new char[8];
			s[0] = getRandomChar();
			s[1] = getRandomChar();
			s[2] = getRandomChar();
			s[3] = getRandomChar();
			s[4] = getRandomChar();
			s[5] = getRandomChar();
			s[6] = getRandomChar();
			s[7] = getStringRandom(1);
			int temp=random.nextInt(8);
			sb.append(s[temp]);
		}
		int s=2+random.nextInt(7);
		return sb.toString().substring(0,s);
	}
	

	public static char getRandomChar() {
		String str = "";
		int hightPos; //
		int lowPos;

		Random random = new Random();

		hightPos = (176 + Math.abs(random.nextInt(39)));
		lowPos = (161 + Math.abs(random.nextInt(93)));

		byte[] b = new byte[2];
		b[0] = (Integer.valueOf(hightPos)).byteValue();
		b[1] = (Integer.valueOf(lowPos)).byteValue();

		try {
			str = new String(b, "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("错误");
		}

		return str.charAt(0);
	}

	// 生成随机数字和字母,
	public static char getStringRandom(int length) {
		Random random = new Random();
		int temp=0;
		String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
		// 输出字母还是数字
		if ("char".equalsIgnoreCase(charOrNum)) {
			// 输出是大写字母还是小写字母
			 temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
			 temp= random.nextInt(26) + temp;
			 return (char)temp;
		}else {
			temp = random.nextInt(10)+48;
			return (char)temp;
		}
		
	}

}