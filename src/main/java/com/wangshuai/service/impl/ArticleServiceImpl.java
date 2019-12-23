package com.wangshuai.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangshuai.dao.ArticleMapper;
import com.wangshuai.entity.Article;
import com.wangshuai.entity.ArticleType;
import com.wangshuai.service.ArticleService;

/** 
 * @ClassName: ArticleServiceImpl 
 * @Description: 文章接口实现
 * @author:王帅  
 * @date: 2019年11月14日 下午1:44:49  
 */
@SuppressWarnings({"unused","rawtypes","unchecked"})
@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleMapper mapper;
	
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public List<Article> getNewArticleList(int count) {
		// TODO 获取最新的文章
		return mapper.getNewArticleList(count);
	}

	@Override
	public  PageInfo<Article> getHotArticleList(int page) {
		if (page != 0) {
			PageHelper.startPage(page, 3);
		}
		List<Article> list = redisTemplate.opsForList().range("hotList", 0, -1);
		if (list != null && list.size() != 0) {
			System.out.println("===========================================Redis查询");
			list.forEach(System.out::println);
			return new PageInfo<Article>(list);
		}
		List<Article> hotArticleList = mapper.getHotArticleList();
		redisTemplate.opsForList().rightPushAll("hotList", hotArticleList.toArray());
		System.out.println("===========================================Mysql查询");
		return new PageInfo<Article>(hotArticleList);
	}

	@Override
	public Article getArticleById(Integer id) {
		mapper.updateArticleCommentCntHitsUp(id);
		return mapper.getArticleById(id);
	}

	@Override
	public PageInfo<Article> getArticleByCG(int chId, int caId, int page) {
		if (page != 0) {
			PageHelper.startPage(page, 5);
		}
		List<Article> articleByCG = mapper.getArticleByCG(chId,caId);
		return new PageInfo<Article>(articleByCG);
	}

	@Override
	public PageInfo<Article> getMyArticle(String page, Integer id) {
		PageHelper.startPage(Integer.parseInt(page),10);
		return new PageInfo<Article>(mapper.getMyArticle(id));
	}

	@Override
	public int deleteArticle(int id) {
		return mapper.deleteArticle(id);
	}

	@Override
	public Article articleIsNull(int id) {
		return mapper.articleIsNull(id);
	}

	@Override
	public PageInfo<Article> getAllArticle(int status, int page) {
		PageHelper.startPage(page, 5);
		List<Article> list = mapper.getAllArticle(status);
		return new PageInfo<Article>(list);
	}

	@Override
	public Article getCheckArticleById(int id) {
		return mapper.getCheckArticleById(id);
	}

	@Override
	public int checkArticleStatus(int id, String status) {
		return mapper.checkArticleStatus(id,status);
	}

	@Override
	public int checkArticleHot(int id, String hot) {
		return mapper.checkArticleHot(id,hot);
	}

	@Override
	public int getArticleNotCheck() {
		return mapper.getArticleNotCheck();
	}

	@Override
	public int addArticle(Article article) {
		return mapper.addArticle(article);
	}

	@Override
	public int updateArticle(Article article) {
		return mapper.updateArticle(article);
	}

	@Override
	public int addImages(Article article) {
		article.setArticleType(ArticleType.IMG);
		return mapper.addImages(article);
	}

	@Override
	public int favoriteArticle(Integer userId, int id) {
		return mapper.favoriteArticle(userId,id);
	}

	@Override
	public PageInfo<Article> getmyFavorite(Integer id, int page) {
		PageHelper.startPage(page, 10);
		List<Article> list = mapper.getmyFavorite(id);
		return new PageInfo<Article>(list);
	}

	@Override
	public int deleteFavorite(Integer id, int articleId) {
		return mapper.deleteFavorite(id,articleId);
	}

}
