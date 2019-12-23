package com.wangshuai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangshuai.dao.LikeMapper;
import com.wangshuai.entity.Like;
import com.wangshuai.service.LikeService;

/** 
 * @ClassName: LikeServiceImpl 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年11月26日 下午1:11:16  
 */
@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikeMapper likeMapper;
	
	@Override
	public void insertLike(Like like) {
		likeMapper.insertLike(like);
	}

}
