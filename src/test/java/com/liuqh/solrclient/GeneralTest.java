package com.liuqh.solrclient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

public class GeneralTest {
	@Test
	public void test1() throws InterruptedException {
		List<String> a = new ArrayList<String>();
		a.add("a");
		a.add("b");
		a.add("c");
		final ArrayList<String> list = new ArrayList<String>(a);
		Thread t = new Thread(new Runnable() {
			int count = -1;
			@Override
			public void run() {
				while (true) {
					list.add(count++ + "");
				}
			}
		});
		t.setDaemon(true);
		t.start();
		Thread.currentThread().sleep(3);
		for (String s : list) {
			System.out.println(s);
		}
	}
	
	@Test
	public void test2() throws InterruptedException{
		List<String> a = new ArrayList<String>();  
        a.add("a");  
        a.add("b");  
        a.add("c");  
        final CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>(a);  
        Thread t = new Thread(new Runnable() {  
            int count = -1;  
  
            @Override  
            public void run() {  
                while (true) {  
                    list.add(count++ + "");
                    list.remove("a");
                }  
            }  
        });  
        t.setDaemon(true);  
        t.start();  
        Thread.currentThread().sleep(3);  
        
        for (String s : list) {  
            System.out.println(s+"-->"+list.hashCode());  
        }  
		
	}
	
	@Test
	public void test3(){
		List<String> myList = new ArrayList<String>();
		 
		myList.add( "1");
		myList.add( "2");
		myList.add( "3");
		myList.add( "4");
		myList.add( "5");
		
		Iterator<String> it = myList.iterator();
		 while (it.hasNext()) {
		     String value = it.next();
		      if (value.equals( "3")) {
		          myList.remove(value);  // error
		     }
		}
	}
	public static void main(String[] args) {

		List<String> myList = new ArrayList<String>();
		 
		myList.add( "1");
		myList.add( "2");
		myList.add( "3");
		myList.add( "4");
		myList.add( "5");
		/*
		for(String s:myList){
			if("3".equals(s)){
				myList.remove(s);
			}
		}*/
		
		Iterator<String> it = myList.iterator();
		 while (it.hasNext()) {
		     String value = it.next();
		      if (value.equals( "3")) {
		          //myList.remove(value);  // error
		          it.remove();
		     }
		}
	}
	@Test
	public void test4() throws InterruptedException{

		List<String> a = new ArrayList<String>();  
        a.add("a");  
        a.add("b");  
        a.add("c");  
        final CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>(a);  
     Thread t=   new Thread(){
        	public void run() {
        		for(int i=0;i<list.size();i++){
        			if(i==0){
        				try {
        					//会输出kkk
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        			}
        			System.out.println("--->"+list.get(i));
        		}
        		/*
        		Iterator<String> it=list.iterator();
        		while(it.hasNext()){
        		//这样子是不会输出kkk的
        			String s=it.next();
        			System.out.println("--->"+s);
        			
        			try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}*/
        	};
        	
        };
		t.setDaemon(true);
        t.start();
        
        Thread.sleep(1);
        list.add("kkk");
        for(int i=0;i<list.size();i++){
			System.out.println("+++++>"+list.get(i));
		}
		
       Thread.sleep(6000);
	}
	@Test
	public void test5(){
		String[] s=new String[]{"1","2","3"};
		String[] s1=new String[]{"2","3","4"};		
		String[] s3=s;
		System.out.println(Arrays.toString(s3));
		s=s1;
		System.out.println(Arrays.toString(s1));
		System.out.println(Arrays.toString(s3));
	}
	@Test
	public void test6(){
		String[] str = new String[] { "a", "b" }; 
		List<String> list = Arrays.asList(str);
		str[0]="c";
		list.add("e");
		System.out.println(list.get(0));
	}
	@Test
	public void test7(){
		String[] wordLibrary={"中国","地图","美国","中国人"};
		String source="我是中国人,我有一张中国人 的地图，你有没有美国的地图''\"\",‘’“”&%￥#@（）{}【】?!!！？？!!!!%*）%￥，。！KTV去符号标号！！当然,，。!!..**半角";
		source=source.replaceAll("\\pP|\\pS|\\pZ", "");
		System.out.println(source);
		int length=4;
		int start=0;
		int index=1;
		Map<String,String> map=new HashMap<String,String>();
		while(start<source.length()-1){
			for(int i=1;i<=length&&start+i<source.length();i++){
				String item=source.substring(start, start+i);
				for(String word:wordLibrary ){
					if(word.equals(item)){
						map.put(String.valueOf(index), item);
						index++;
					}
				}
			}
			start++;
		}
		System.out.println(map);
	}
	
	@Test
	public void test9(){
		int a=2;
		switch (a) {
		case 1:
		case 2:
		case 3:
			System.out.println(a);
			break;

		default:
			System.out.println("default");
			break;
		}
	}
	
	@Test
	public void test8() throws UnsupportedEncodingException{
		String s="http://localhost:8483/solr/core2/select?facet.field=content1&facet.query=facet.mincount=1&facet=on&indent=on&q=content1:%22%E4%B8%AD%E5%9B%BD%E6%B7%B1%E5%9C%B3%E6%8B%8D%E6%8B%8D%22&wt=json";
		System.out.println(URLDecoder.decode(s,"utf8"));
	}
}
