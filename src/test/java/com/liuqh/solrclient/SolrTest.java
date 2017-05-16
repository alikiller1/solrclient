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
		map.put("id", "5");
		map.put("name", "lijie");
		map.put("features", "小眼睛，长头发");
		map.put("age", "24");
		map.put("addr", "深圳");
		SolrMain.addDocument(map, "core1");
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