package com.liuqh.solrclient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Collation;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Correction;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class SolrMain {

    /**
     * solr http服务地址
     */
    public static String SOLR_URL="http://localhost:8483/solr";

    /**
     * solr的core
     */
    public static String SOLR_CORE="core2";

    static {/*
        Properties properties = new Properties();
        String path = SolrMain.class.getResource("/").getFile().toString()
                + "solr.properties";
        try {
            FileInputStream fis = new FileInputStream(new File(path));
            properties.load(fis);
            SOLR_URL = properties.getProperty("SOLR_URL");
            SOLR_CORE = properties.getProperty("SOLR_CORE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    */
    	}

    

    /**
     * 获取solr服务
     * 
     * @return
     */
    public static HttpSolrClient getSolrClient(String core) {
        HttpSolrClient hsc=new HttpSolrClient.Builder(SOLR_URL + core).build();
        return hsc;
    }

    /**
     * 添加文档
     * 
     * @param map
     * @param core
     * @throws Exception
     */
    public static void addDocument(Map<String, String> map)
            throws Exception {
        SolrInputDocument sid = new SolrInputDocument();
        for (Entry<String, String> entry : map.entrySet()) {
            sid.addField(entry.getKey(), entry.getValue());
        }
        HttpSolrClient solrClient = getSolrClient("/" + SOLR_CORE);
        solrClient.add(sid);
        commitAndCloseSolr(solrClient);
    }

    /**
     * 添加文档，通过bean方式
     * 
     * @param persons
     * @param core
     * @throws Exception
     */
    public static void addDocumentByBean(List<Person> persons)
            throws Exception {
        HttpSolrClient solrClient = getSolrClient("/" + SOLR_CORE);
        solrClient.addBeans(persons);
        commitAndCloseSolr(solrClient);
    }

    /**
     * 根据id集合删除索引
     * 
     * @param ids
     * @param core
     * @throws Exception
     */
    public static void deleteDocumentByIds(List<String> ids)
            throws Exception {
        HttpSolrClient solrClient = getSolrClient("/" + SOLR_CORE);
        solrClient.deleteById(ids);
        commitAndCloseSolr(solrClient);
    }

    public static void getDocument() throws Exception {
        HttpSolrClient solrClient = getSolrClient("/" + SOLR_CORE);
        SolrQuery sq = new SolrQuery();

        // q查询
        //sq.set("q", "addr:深圳 feature:矮");
        sq.set("q", "深圳 矮");
        
        //sq.set("q", "*:*");
        
        //设置Query parser，要权重排序，那么需要用到Dismax
        sq.set("defType","dismax");
        //默认的查询字段，一般默认指定。
        sq.set("df", "searchText");
        //设置参数的属性，区分大小写
        sq.setParam("q.op", "AND");
        //设置查询字段的权重
        //sq.set("qf","addr^1 feature^0.1");

        // filter查询
        //sq.addFilterQuery("name:[jely jim]");
       // sq.addFilterQuery("age:[10 TO 20]");

        // 排序
        //sq.setSort("id", SolrQuery.ORDER.desc);

        // 分页 从第0条开始取，取一条
        sq.setStart(0);
        sq.setRows(10);
        

        // 设置高亮
        sq.setHighlight(true);
        
        // 设置高亮的字段
        sq.addHighlightField("name");

        // 设置高亮的样式
        sq.setHighlightSimplePre("<font color='red'>");
        sq.setHighlightSimplePost("</font>");

        QueryResponse result = solrClient.query(sq);

        // 这里可以从result获得查询数据(两种方式如下)

        // 1.获取document数据
        System.out.println("1.获取document数据-------------------------");
        SolrDocumentList results = result.getResults();
        // 获取查询的条数
        System.out.println("一共查询到" + results.getNumFound() + "条记录");
        String id="";
        for (SolrDocument solrDocument : results) {
        	id=id+solrDocument.get("id");
            System.out.println("id:" + solrDocument.get("id"));
            System.out.println("name:" + solrDocument.get("name"));
            System.out.println("age:" + solrDocument.get("age"));
            System.out.println("addr:" + solrDocument.get("addr"));
            System.out.println("searchText:" + solrDocument.get("searchText"));
        }
        System.out.println("排序->"+id);

        // 2.获取对象信息,需要传入对应对象的类class
        System.out.println("2.获取对象信息,需要传入对应对象的类class-----------");
        List<Person> persons = result.getBeans(Person.class);
        System.out.println("一共查询到" + persons.size() + "条记录");
        for (Person person : persons) {
            System.out.println(person);
        }
        commitAndCloseSolr(solrClient);
    }

    /**
     * 查询使用spell接口，输入错误，solr可以给出建议词
     * 
     * @param core
     * @throws Exception
     */
    public static void getSpell(String keyWork) throws Exception {
        HttpSolrClient solrClient = getSolrClient("/" + SOLR_CORE);
        SolrQuery sq = new SolrQuery();
        sq.set("qt", "/spell");

        // 原本是lisi，这里拼写错误，测试solr返回建议词语
        sq.set("q", keyWork);
        QueryResponse query = solrClient.query(sq);
        SolrDocumentList results = query.getResults();

        // 获取查询条数
        long count = results.getNumFound();

        // 判断是否查询到
        if (count == 0) {
            SpellCheckResponse spellCheckResponse = query
                    .getSpellCheckResponse();
            List<Collation> collatedResults = spellCheckResponse
                    .getCollatedResults();
            for (Collation collation : collatedResults) {
                long numberOfHits = collation.getNumberOfHits();
                System.out.println("建议条数为:" + numberOfHits);

                List<Correction> misspellingsAndCorrections = collation
                        .getMisspellingsAndCorrections();
                for (Correction correction : misspellingsAndCorrections) {
                    String source = correction.getOriginal();
                    String current = correction.getCorrection();
                    System.out.println("推荐词语为：" + current + "原始的输入为：" + source);
                }
            }
        } else {
            for (SolrDocument solrDocument : results) {
                // 获取key集合
                Collection<String> fieldNames = solrDocument.getFieldNames();

                // 根据key集合输出value
                for (String field : fieldNames) {
                    System.out.println("key: " + field + ",value: "
                            + solrDocument.get(field));
                }
            }
        }

        // 关闭连接
        commitAndCloseSolr(solrClient);
    }

    /**
     * 提交以及关闭服务
     * 
     * @param solrClient
     * @throws Exception
     */
    public static void commitAndCloseSolr(HttpSolrClient solrClient)
            throws Exception {
        solrClient.commit();
        solrClient.close();
    }
    
    public static void fullDataImport(){
    	String url="http://localhost:8483/solr/core2/dataimport?command=full-import&clean=true&entity=student2&commit=true";
    	String s= HttpRequest.sendGet(url, null);
    	System.out.println(s);
    }
    
    public static void deltaDataImport(){
    	String url="http://localhost:8483/solr/core2/dataimport?command=delta-import";
    	String s= HttpRequest.sendGet(url, null);
    	System.out.println(s);
    }
    
    /**
     * 主函数入口
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
    	fullDataImport();
    }

}