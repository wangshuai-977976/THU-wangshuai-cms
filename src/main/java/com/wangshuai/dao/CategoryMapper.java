package com.wangshuai.dao;

import java.util.List;

import com.wangshuai.entity.Category;

/** 
 * @ClassName: CategoryMappper 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年11月14日 下午7:45:49  
 */
public interface CategoryMapper {

	/** 
	 * @Title: getCateListByChannelId 
	 * @Description: TODO
	 * @param chId
	 * @return: void
	 * @date: 2019年11月14日下午7:45:59
	 */
	List<Category> getCateListByChannelId(int chId);

}
