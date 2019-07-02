package com.offcn.solr.test;

import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.request.CollectionAdminRequest.Create;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;

import com.offcn.pojo.TbItem;

public class Demo1 {

	static ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("classpath:solr.xml");
	static SolrTemplate template = (SolrTemplate) app.getBean("solrTemplate");
	
	public static void main(String[] args) {
		
		save();
		
//		getData();
		
//		deleteData();
		
		saveAll();
		
//		deleteAllData();
		
//		fenyefind();
		
		
//		tiaojiansearch();
		
		

	}

	// 添加数据
	public static void save(){
		TbItem item = new TbItem();
		item.setId(1l);
		item.setPrice(new BigDecimal(2000));
		item.setTitle("手机");
		// save
		template.saveBean(item);
		template.commit();
		
		
		System.out.println("ok");
	}
	
	public static void saveAll(){
		List<TbItem> list = new ArrayList<TbItem>();
		for(long i = 0; i < 100; i++){
			TbItem item = new TbItem();
			item.setId(i);
			item.setTitle("手机"+i);
			item.setPrice(new BigDecimal(2000+i));
			list.add(item);
		}
		// 添加多条数据
		template.saveBeans(list);
		template.commit();
	}
	// 根据id查询
	public static void getData(){
		TbItem tbItem = template.getById(1l, TbItem.class);
		System.out.println(tbItem.getTitle());
	}
	
	// 删除
	public static void deleteData(){
		template.deleteById("1");
		template.commit();
		System.out.println("ok");
	}
	
	
	// 分页查询
	
	public static void fenyefind(){
		Query query = new SimpleQuery("*:*");
		// 设置开始索引和每页的数据
		query.setOffset(0);
		query.setRows(20);
		
		// 开始分页查询
		ScoredPage<TbItem> page = template.queryForPage(query, TbItem.class);
		System.out.println(page.getTotalElements()+"总条目数");
		
		for(TbItem item : page){
			System.out.println(item.getTitle());
		}
		
	}
	
	
	// 删除solr中的所有的数据
	public static void deleteAllData(){
		Query query = new SimpleQuery("*:*");
		template.delete(query);
		template.commit();
		System.out.println("删除所有数据");
	}
	
	public static void tiaojiansearch(){
		Query query = new SimpleQuery("sellerId:1");
//		Criteria criteria = new Criteria("title").contains("手机");
//		criteria.and("title").
//		query.addCriteria(criteria);
		
		// 设置分页
//		query.setOffset(0);
//		query.setRows(100);
		
		ScoredPage<TbItem> list = template.queryForPage(query, TbItem.class);
		
		for(TbItem item : list){
			System.out.println(item.getTitle());
		}
		
	}
}






























