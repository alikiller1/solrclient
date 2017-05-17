package com.liuqh.solrclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SolrTest {

	@Test
	public void testQuery() throws Exception {
		SolrMain.getDocument("core1");
	}

	@Test
	public void testAdd() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "1");
		map.put("name", "liujie");
		map.put("features", "小眼睛，长头发");
		map.put("age", "24");
		map.put("addr", "深圳，福田");
		SolrMain.addDocument(map, "core1");
	}
	
	@Test
	public void testAddDocumentByBean() throws Exception{
		List<Person> list=new ArrayList<Person>();
		Person p1=new Person();
		p1.setId("101");
		p1.setAddr("河北，唐山");
		p1.setName("liujie");
		p1.setAge(15);
		Person p2=new Person();
		p2.setId("103");
		p2.setAddr("山东，济南");
		p2.setName("刘朋");
		p2.setAge(14);
		Person p3=new Person();
		p3.setId("104");
		p3.setAddr("深圳，罗湖");
		p3.setName("小明");
		p3.setAge(14);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		SolrMain.addDocumentByBean(list, "core1");
		
	}
	
	@Test
	public void testDel() throws Exception{
		List<String> ids=new ArrayList<String>();
		ids.add("1");
		ids.add("2");
		ids.add("3");
		ids.add("4");
		SolrMain.deleteDocumentByIds(ids, "core1");
	}
}