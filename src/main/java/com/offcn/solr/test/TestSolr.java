package com.offcn.solr.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.offcn.pojo.TbItem;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:solr.xml")
public class TestSolr {

    @Autowired
    private SolrTemplate solrTemplate;

    // 保存修改
    @Test
    public void saveOrUpdate() throws Exception {
        TbItem item = new TbItem();
        item.setId(1L);
        item.setTitle("华为荣耀");
        item.setImage("http://192.168.25.133/group1/M00/00/00/wKgZhVt2dxyAGd_NAAAXideDuZI485.jpg");
        item.setBrand("华为");
        item.setCategory("手机");
        item.setSeller("华为小店");
        item.setPrice(new BigDecimal(200.01));
        item.setGoodsId(1L);

        solrTemplate.saveBean(item);
        solrTemplate.commit();
    }

    // 根据id查询
    @Test
    public void findById() throws Exception {
        TbItem item = solrTemplate.getById(1L, TbItem.class);
        System.out.println(item.getTitle());
    }

    // 根据id进行删除
    @Test
    public void delById() throws Exception {
        solrTemplate.deleteById("1");
        solrTemplate.commit();
    }

    // 批量导入
    @Test
    public void batchAdd() throws Exception {

        List<TbItem> list = new ArrayList<TbItem>();
        for (int i = 0; i < 100; i++) {
            TbItem item = new TbItem();
            item.setId(1L + i);
            item.setTitle("华为荣耀" + i);
            item.setImage("http://192.168.25.133/group1/M00/00/00/wKgZhVt2dxyAGd_NAAAXideDuZI485.jpg");
            item.setBrand("华为");
            item.setCategory("手机");
            item.setSeller("华为小店");
            item.setPrice(new BigDecimal(200.01 + i));
            item.setGoodsId(1L + i);
            list.add(item);
        }
        solrTemplate.saveBeans(list);
        solrTemplate.commit();
    }

    // 分页查询
    @Test
    public void pageQuery() throws Exception {

        Query query = new SimpleQuery("*:*");
        query.setPageRequest(new PageRequest(0, 20));
//        query.setOffset(0);// 起始条数
//        query.setRows(20);// 查询多少条
        ScoredPage<TbItem> pageResult = solrTemplate.queryForPage(query, TbItem.class);
        System.out.println("总记录数:" + pageResult.getTotalElements());
        System.out.println("总页数: " + pageResult.getTotalPages());
        for (TbItem tbItem : pageResult.getContent()) {
            System.out.println(tbItem.getTitle() + " " + tbItem.getPrice());
        }
    }

    // 条件查询
    @Test
    public void criteriaQuery() throws Exception {
        Query query = new SimpleQuery();
        // 构建条件
        Criteria criteria = new Criteria("item_category").contains("手");
        criteria = criteria.and("id").contains("2");
        criteria = criteria.and("item_title").contains("2");
        query.addCriteria(criteria);
        // 分页
        query.setPageRequest(new PageRequest(0, 10));// (起始页,每页显示条数)
        ScoredPage<TbItem> pageResult = solrTemplate.queryForPage(query, TbItem.class);
        System.out.println("总记录数:" + pageResult.getTotalElements());
        System.out.println("总页数: " + pageResult.getTotalPages());
        for (TbItem tbItem : pageResult.getContent()) {
            System.out.println(tbItem.getId() + " " + tbItem.getTitle() + " " + tbItem.getPrice());
        }
    }

    // 删除所有
    @Test
    public void delAll() throws Exception {
        Query query = new SimpleQuery("*:*");
        solrTemplate.delete(query);
        solrTemplate.commit();
    }

}