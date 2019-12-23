/**   
 * Copyright © 2019 THU. All rights reserved.
 * 
 * @Title: ArticleResp.java 
 * @Prject: Senior-wangshuai-cms
 * @Package: com.wangshuai.dao 
 * @Description: TODO
 * @author: 王帅  
 * @date: 2019年12月23日 上午11:07:17 
 * @version: V1.0   
 */
package com.wangshuai.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.wangshuai.entity.Article;

/** 
 * @ClassName: ArticleResp 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年12月23日 上午11:07:17  
 */
public interface ArticleResp extends ElasticsearchRepository<Article, Integer>{
	
		List<Article> findByTitle(String key);
}
