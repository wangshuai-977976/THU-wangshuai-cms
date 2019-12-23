package com.wangshuai.service;

import com.wangshuai.entity.Like;

/** 
 * @ClassName: LikeService 
 * @Description: 点赞
 * @author:王帅  
 * @date: 2019年11月26日 下午1:10:42  
 */
public interface LikeService {

	/** 
	 * @Title: likeArticle 
	 * @Description: TODO
	 * @param id
	 * @param id2
	 * @return: void
	 * @date: 2019年11月26日下午1:12:31
	 */
	void insertLike(Like like);

}
