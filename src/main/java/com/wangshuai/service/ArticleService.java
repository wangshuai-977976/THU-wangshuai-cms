package com.wangshuai.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wangshuai.entity.Article;

/** 
 * @ClassName: ArticleService 
 * @Descriptio 文章接口
 * @author:王帅  
 * @date: 2019年11月14日 下午1:44:30  
 */
public interface ArticleService {

	/** 
	 * @Title: getNewArticleList 
	 * @Description: 获取最新的文章
	 * @param i
	 * @return: void
	 * @date: 2019年11月14日下午1:49:44
	 */
	List<Article> getNewArticleList(int count);

	/** 
	 * @Title: getHotArticleList 
	 * @Description: 获取热门文章
	 * @return: void
	 * @date: 2019年11月14日下午2:09:06
	 */
	PageInfo<Article> getHotArticleList(int page);

	/** 
	 * @Title: getArticleById 
	 * @Description: 查看文章香型
	 * @param id
	 * @return: void
	 * @date: 2019年11月14日下午6:53:57
	 */
	Article getArticleById(Integer id);

	/** 
	 * @Title: getArticleByCG 
	 * @Description: 根据斌到获取文章
	 * @param chId
	 * @param caId
	 * @param page
	 * @return: void
	 * @date: 2019年11月14日下午7:32:09
	 */
	PageInfo<Article> getArticleByCG(int chId, int caId, int page);

	/** 
	 * @Title: getMyArticle 
	 * @Description: 获取我的文章
	 * @param page
	 * @param id
	 * @return: void
	 * @date: 2019年11月18日下午7:02:55
	 */
	PageInfo<Article> getMyArticle(String page, Integer id);

	/** 
	 * @Title: deleteArticle 
	 * @Description: 根据id删除文章
	 * @param id
	 * @return: void
	 * @date: 2019年11月18日下午7:48:45
	 */
	int deleteArticle(int id);

	/** 
	 * @Title: articleIsNull 
	 * @Description: 判段文章是否存在
	 * @param id
	 * @return
	 * @return: Article
	 * @date: 2019年11月18日下午7:57:34
	 */
	Article articleIsNull(int id);

	/** 
	 * @Title: getAllArticle 
	 * @Description: 获取所有文章
	 * @param status
	 * @param page
	 * @return: void
	 * @date: 2019年11月19日下午1:54:51
	 */
	PageInfo<Article> getAllArticle(int status, int page);

	/** 
	 * @Title: getCheckArticleById 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: Article
	 * @date: 2019年11月19日下午7:15:49
	 */
	Article getCheckArticleById(int id);

	/** 
	 * @Title: checkArticleStatus 
	 * @Description: TODO
	 * @param id
	 * @param status
	 * @return: void
	 * @date: 2019年11月19日下午7:36:22
	 */
	int checkArticleStatus(int id, String status);

	/** 
	 * @Title: checkArticleHot 
	 * @Description: 改标热门状态
	 * @param id
	 * @param hot
	 * @return: void
	 * @date: 2019年11月19日下午8:08:35
	 */
	int checkArticleHot(int id, String hot);

	/** 
	 * @Title: getArticleNotCheck 
	 * @Description: 获取未审核文章数量
	 * @return: void
	 * @date: 2019年11月20日上午8:39:41
	 */
	int getArticleNotCheck();

	/** 
	 * @Title: addArticle 
	 * @Description: 添加文章
	 * @param article
	 * @return: void
	 * @date: 2019年11月20日下午6:29:42
	 */
	int addArticle(Article article);

	/** 
	 * @Title: updateArticle 
	 * @Description: TODO
	 * @param article
	 * @return
	 * @return: int
	 * @date: 2019年11月20日下午8:17:28
	 */
	int updateArticle(Article article);

	/** 
	 * @Title: addImages 
	 * @Description: 添加图片集
	 * @param article
	 * @return
	 * @return: int
	 * @date: 2019年11月22日下午1:18:36
	 */
	int addImages(Article article);

	/** 
	 * @Title: favoriteArticle 
	 * @Description: TODO
	 * @param id
	 * @param id2
	 * @return: void
	 * @date: 2019年11月22日下午1:45:56
	 */
	int favoriteArticle(Integer id, int id2);

	/** 
	 * @Title: getmyFavorite 
	 * @Description: 获取收藏的所有文章
	 * @param id
	 * @param page
	 * @return: void
	 * @date: 2019年11月22日下午3:34:45
	 */
	PageInfo<Article> getmyFavorite(Integer id, int page);

	/** 
	 * @Title: deleteFavorite 
	 * @Description: TODO
	 * @param id
	 * @param articleId
	 * @return: void
	 * @date: 2019年11月22日下午7:09:32
	 */
	int deleteFavorite(Integer id, int articleId);



}
