package com.liuqh.search;

import org.apache.solr.common.params.SolrParams;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.search.QParser;
import org.apache.solr.search.QParserPlugin;

public class MyQParserPlugin extends QParserPlugin{
	public static final String NAME = "myparser";
	
	@Override
	public QParser createParser(String qstr, SolrParams localParams, SolrParams params, SolrQueryRequest req) {
		return new MyQparser(qstr, localParams, params, req);
	}
	
	@Override
	public void init(NamedList args) {
		super.init(args);
	}

}
