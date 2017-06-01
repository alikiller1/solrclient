package com.liuqh.search;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.complexPhrase.ComplexPhraseQueryParser;
import org.apache.lucene.search.Query;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.parser.QueryParser;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.search.QParser;
import org.apache.solr.search.QueryParsing;
import org.apache.solr.search.SyntaxError;

public class MyQparser extends QParser {

    ComplexPhraseQueryParser lparser;

    boolean inOrder = true;

    /**
     * When <code>inOrder</code> is true, the search terms must
     * exists in the documents as the same order as in query.
     *
     * @param inOrder parameter to choose between ordered or un-ordered proximity search
     */
    public void setInOrder(final boolean inOrder) {
      this.inOrder = inOrder;
    }

    public MyQparser(String qstr, SolrParams localParams, SolrParams params, SolrQueryRequest req) {
      super(qstr, localParams, params, req);
    }

    @Override
    public Query parse() throws SyntaxError {
      String qstr = getString();

      String defaultField = getParam(CommonParams.DF);
      if (defaultField == null) {
        defaultField = getReq().getSchema().getDefaultSearchFieldName();
      }

      lparser = new ComplexPhraseQueryParser(defaultField, getReq().getSchema().getQueryAnalyzer());

      if (localParams != null)
        inOrder = localParams.getBool("inOrder", inOrder);

      lparser.setInOrder(inOrder);

      QueryParser.Operator defaultOperator = QueryParsing.getQueryParserDefaultOperator(getReq().getSchema(), getParam(QueryParsing.OP));

      if (QueryParser.Operator.AND.equals(defaultOperator))
        lparser.setDefaultOperator(org.apache.lucene.queryparser.classic.QueryParser.Operator.AND);
      else
        lparser.setDefaultOperator(org.apache.lucene.queryparser.classic.QueryParser.Operator.OR);
      
      try {
    	System.out.println("+++++++++++++++++++++++++++++++++++++++++"+defaultOperator);
    	lparser.setDefaultOperator(org.apache.lucene.queryparser.classic.QueryParser.Operator.AND);
        return lparser.parse(qstr);
      } catch (ParseException pe) {
        throw new SyntaxError(pe);
      }
    }

    @Override
    public String[] getDefaultHighlightFields() {
      return lparser == null ? new String[]{} : new String[]{lparser.getField()};
    }
  }
