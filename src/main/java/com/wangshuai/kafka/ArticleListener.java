package com.wangshuai.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.wangshuai.dao.ArticleMapper;
import com.wangshuai.entity.Article;

public class ArticleListener implements MessageListener<String, String>{
	//注入ArticleMapper
	@Autowired
	ArticleMapper articleMapper;
	@Autowired
	RedisTemplate redisTemplate;
	//收消息的方法
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		String jsonString = data.value();
		//你是怎么知道这个消息是修改操作或者是接收的文章呢?
		if(jsonString.startsWith("update")) {
			System.err.println(jsonString);
			//如果是update开头的消息,我们就认为是通知修改的操作
			redisTemplate.delete("hot_articles");
			//es数据据库修改TODO
		}else if(jsonString.startsWith("del")) {
			System.err.println(jsonString);
			redisTemplate.delete("hot_articles");
			//es数据据库删除TODO
		}else if(jsonString.startsWith("add")) {
			System.err.println(jsonString);
			//说明是添加操作
			redisTemplate.delete("hot_articles");
			//es数据据库添加TODO
		}
		
		else {
			
			System.err.println("收到了消息:........"+jsonString);
			String[] split = jsonString.split("=");
			Article article = JSON.parseObject(split[1], Article.class);
			//保存到mysql
			articleMapper.addArticle(article);
		}
	}

}
