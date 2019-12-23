package com.wangshuai.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangshuai.dao.LinkMappper;
import com.wangshuai.entity.Link;
import com.wangshuai.service.LinkService;

/** 
 * @ClassName: LinkServiceImpl 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年11月23日 下午6:35:03  
 */
@Service
public class LinkServiceImpl implements LinkService {

	@Autowired
	private LinkMappper linkMapper;

	@Override
	public int addLink(Link link) {
		return linkMapper.addLink(link);
	}

	@Override
	public PageInfo<Link> getLinks(int page) {
		PageHelper.startPage(page, 10);
		List<Link> list = linkMapper.getLinks();
		return new PageInfo<Link>(list);
	}

	@Override
	public int deleteLink(int id) {
		return linkMapper.deleteLink(id);
	}

	@Override
	public PageInfo<Link> myFavorite(int page, Integer id) {
		PageHelper.startPage(page, 10);
		List<Link> list = linkMapper.getmyFavorite(id);
		return new PageInfo<Link>(list);
	}
	
	
}
