package com.liuqh.solrclient;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
		String[] wordLibrary={"我","是","人","有","一张","的","你","有没有","没有","中国","地图","美国","中国人"};
		String source="我是中国人,我有一张中国人 的地图，你有没有美国的地图''\"\",‘’“”&%￥#@（）{}【】?!!！？？!!!!%*）%￥";
		source=source.replaceAll("\\pP|\\pS|\\pZ", "");
		System.out.println(source);
		int length=3;
		int start=0;
		int index=0;
		Map<String,String> map=new LinkedHashMap<String,String>();
		boolean findFlag=false;
		while(start<source.length()){
			for(int i=1;i<=length&&start+i<=source.length();i++){
				findFlag=false;
				String item=source.substring(start, start+i);
				for(String word:wordLibrary ){
					if(word.equals(item)){
						map.put(String.valueOf(index), item);
						index++;
						start=start+i;
						findFlag=true;
						break;
					}
				}
				if(findFlag){
					break;
				}
			}
			if(!findFlag){
				start++;
			}
			
			
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
		String s="http://localhost:8483/solr/core2/select?_=1499170903149&defType=dismax&fl=id,score&indent=on&q=addr:%E6%B7%B1%E5%9C%B3++feature:%E7%9F%AE&q.op=OR&qf=addr%5E0.1+feature%5E10&wt=json";
		System.out.println(URLDecoder.decode(s,"utf8"));
	}
	
	@Test
	public void test10() throws IOException{
		RandomAccessFile file=new RandomAccessFile("d:/a.txt", "rw");
		file.seek(0);
	    char a1=(char) file.readByte();
		System.out.println(a1);
		//file.writeInt(1000);
		file.seek(10);
		int a=file.readInt();
		System.out.println(a);
		file.seek(11);
		//file.writeChars("abc123");
		char c=file.readChar();
		System.out.println(c);
		file.close();
	}
	
	@Test
	public void test11(){
		long startTime=System.currentTimeMillis();
		String s=null;
		int count=0;
		for(int i=0;i<=20000000;i++){
			s="我们都是中国人中国人"+i;
			count++;
			if(s.startsWith("我们")){
				
			}
			if(s.indexOf("都是")>-1){
				
			}
		}
		System.out.println("use time="+(System.currentTimeMillis()-startTime));
		System.out.println("count="+count);
	}
	@Test
	public void test12() throws Exception{
		String keyword="addr:深圳  feature:矮";
		String qf="addr^10 feature^1";
		String url="http://localhost:8483/solr/core2/select?_=1499170903149&defType=dismax&fl=id,score&indent=on&wt=json&q.op=OR";
		url=url+"&q="+URLEncoder.encode(keyword,"UTF-8")+"&qf="+URLEncoder.encode(qf,"UTF-8");
		String resp=HttpClientUtil.get(url);
		//String resp=HttpRequest.sendGet(url, null);
		Thread.sleep(1000000);
		System.out.println("resp-->"+resp);
	}
}
