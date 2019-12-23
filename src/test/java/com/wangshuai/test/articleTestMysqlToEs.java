/**   
 * Copyright © 2019 THU. All rights reserved.
 * 
 * @Title: articleTestMysqlToEs.java 
 * @Prject: Senior-wangshuai-cms
 * @Package: com.wangshuai.test 
 * @Description: TODO
 * @author: 王帅  
 * @date: 2019年12月18日 上午10:47:44 
 * @version: V1.0   
 */
package com.wangshuai.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangshuai.dao.ArticleMapper;
import com.wangshuai.dao.ArticleResp;
import com.wangshuai.entity.Article;

/** 
 * @ClassName: articleTestMysqlToEs 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年12月18日 上午10:47:44  
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class articleTestMysqlToEs {
	@Autowired
	private ArticleMapper articleMapper;
	
	//对es数据库进行crud
	@Autowired
	private ArticleResp articleResp;
	@Test
	public void testMySqlToEs() {
		//从mysql查询文章集合封装到list集合 
		List<Article> list = articleMapper.getAll();
		articleResp.saveAll(list);
	}
}
