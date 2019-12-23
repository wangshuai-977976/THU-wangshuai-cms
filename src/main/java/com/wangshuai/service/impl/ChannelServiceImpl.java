package com.wangshuai.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangshuai.dao.ChannelMapper;
import com.wangshuai.entity.Category;
import com.wangshuai.entity.Channel;
import com.wangshuai.service.ChannelService;

/** 
 * @ClassName: ChanelServiceImpl 
 * @Description:频道接口
 * @author:王帅  
 * @date: 2019年11月14日 下午1:15:40  
 */
@Service
public class ChannelServiceImpl implements ChannelService{
	
	@Autowired
	private ChannelMapper mapper;

	@Override
	public List<Channel> getChannelList() {
		return mapper.getChannelList();
	}

	
	

}
