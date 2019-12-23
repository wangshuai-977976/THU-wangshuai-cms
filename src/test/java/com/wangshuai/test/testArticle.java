/**   
 * Copyright © 2019 THU. All rights reserved.
 * 
 * @Title: testArticle.java 
 * @Prject: Senior-wangshuai-cms
 * @Package: com.wangshuai.test 
 * @Description: TODO
 * @author: 王帅  
 * @date: 2019年12月23日 上午8:30:06 
 * @version: V1.0   
 */
package com.wangshuai.test;

import java.io.File;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.wangshuai.entity.Article;
import com.wangshuai.entity.Channel;
import com.wangshuai.service.ChannelService;
import com.wangshuai.utils.StringUtils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;


/** 
 * @ClassName: testArticle 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年12月23日 上午8:30:06  
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class testArticle {
	
	@Autowired
	ChannelService channelService;
	@Autowired
	KafkaTemplate kafkaTemplate;
	@Test
	public void demo() {
		//String readString = FileUtil.readString("D://1707DD","utf-8");
		File file = new File("D:\\1707DD");
		File[] list = file.listFiles();
		System.err.println(list.length+"文件个数是-----------------------------------------------------------------------------------");
		int i =0;
		for (File file2 : list) {
			i++;
			Random random = new Random();
			Article article = new Article();
			article.setTitle(file2.getName().split("\\.")[0]);
			String string = FileUtil.readString(file2, "utf-8");
			if(string.length()>=140) {
				article.setContent(string.substring(0, 139));
			}else {
				article.setContent(string);
			}
			
			List<Channel> channelList = channelService.getChannelList();
			article.setChannelId(String.valueOf(channelList.get(random.nextInt(channelList.size())).getId()));
			article.setHits(StringUtils.getRandomNum()+"");
			article.setHot(String.valueOf(random.nextInt(2)));
			article.setCreated(DateUtil.parseDate("2019-01-01"));
			System.err.println(i+"文章对象是================================="+article);
		//	kafkaTemplate.sendDefault("qaddMYSQL="+JSON.toJSONString(article));
			kafkaTemplate.send("articles", "添加add="+JSON.toJSONString(article));
		}
	}
}
