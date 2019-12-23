package com.wangshuai.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangshuai.dao.CategoryMapper;
import com.wangshuai.entity.Category;
import com.wangshuai.service.CategoryService;

/** 
 * @ClassName: CategoryService 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年11月14日 下午7:44:15  
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryMapper mapper;

	@Override
	public List<Category> getCateListByChannelId(int chId) {
		return mapper.getCateListByChannelId(chId);
	}

}
