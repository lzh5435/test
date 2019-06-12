package com.offcn.solr.test;

import java.io.IOException;

import org.apache.http.client.HttpClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

/**
 * demo1和demo2不可以共存，因为solr-solrj和spring-data-solr包不可以共存
 * @author luoyanpeng
 *
 */
public class Demo2 {

	/*public static void main(String[] args) throws Exception {
		String url = "http://10.10.84.201:8983/solr/new_core";
//		add(url);
		getData(url);
	}
	 
	public static void add(String url) throws Exception{
		HttpSolrClient server = new HttpSolrClient(url);
		SolrInputDocument doucemnt = new SolrInputDocument();
		doucemnt.addField("id", 1);
		doucemnt.addField("name", "xiaocang");
		doucemnt.addField("age", 18);
		server.add(doucemnt);
		server.commit();
		System.out.println("ok");
	}
	
	public static void getData(String url) throws Exception{
		HttpSolrClient server = new HttpSolrClient(url);
		SolrQuery query = new SolrQuery("name:xiaocang");
//		query.setStart(0);
//		query.setRows(10);
		
		// 查询
		QueryResponse response = server.query(query);
		SolrDocumentList rs = response.getResults();
		for(SolrDocument list :rs){
			System.out.println(list);
		}
		
	}
*/
}



















