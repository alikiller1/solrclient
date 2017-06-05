package com.liuqh.solrclient;


import java.util.Set;
import java.util.TreeSet;

public class CCC {
	
	public static void main(String[] args) {
		Man man1=new Man("张三",1,"男");
		Man man2=new Man("李四",2,"男");
		Man man3=new Man("王五",3,"女");
		Man man4=new Man("陈六",1,"男");
		Set<Man> set=new TreeSet<>();
		set.add(man1);
		set.add(man2);
		set.add(man3);
		set.add(man4);
		for (Man man : set) {
			System.out.println(man);
		}
		
	}
	
	
}
class Man implements Comparable<Object>{
	String name;
	Integer age;
	String sex;
	
	public Man(String name, Integer age, String sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Man [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof Man){
			Man man=(Man)o;
			int num;
			num=this.age.compareTo(man.age);
			if(num==0){
				return num=this.sex.compareTo(man.sex);
			}else{
				return num;
			}
		}
		return 0;
	}
}
