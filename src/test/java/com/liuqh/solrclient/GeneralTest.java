package com.liuqh.solrclient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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
}
