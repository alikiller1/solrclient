package com.liuqh.solrclient;

import org.junit.Test;

public class ZookeeperTest {
	
	@Test
	public void test1(){
		User u=new User();
		u.setId(1);
		u.setName("aaaccc");
		Zookeeper.writeData(u);
	}
}
