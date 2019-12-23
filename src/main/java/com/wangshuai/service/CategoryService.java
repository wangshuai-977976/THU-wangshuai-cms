package com.wangshuai.service;

import java.util.List;

import com.wangshuai.entity.Category;

/** 
 * @ClassName: CategoryService 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年11月14日 下午7:43:59  
 */
public interface CategoryService {

	/** 
	 * @Title: getCateListByChannelId 
	 * @Description: TODO
	 * @param chId
	 * @return: void
	 * @date: 2019年11月14日下午7:44:43
	 */
	List<Category> getCateListByChannelId(int chId);

}
