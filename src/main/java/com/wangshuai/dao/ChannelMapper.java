package com.wangshuai.dao;

import java.util.List;

import com.wangshuai.entity.Category;
import com.wangshuai.entity.Channel;

/** 
 * @ClassName: ChanelMapper 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年11月14日 下午1:16:13  
 */
public interface ChannelMapper {

	/** 
	 * @Title: getChannelList 
	 * @Description: TODO
	 * @return: void
	 * @date: 2019年11月14日下午1:23:38
	 */
	public List<Channel> getChannelList();


	
}
