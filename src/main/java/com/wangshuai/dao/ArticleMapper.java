package com.wangshuai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wangshuai.entity.Article;
import com.wangshuai.entity.Comment;

/** 
 * @ClassName: ArticleMapper 
 * @Description: 文章Dao层
 * @author:王帅  
 * @date: 2019年11月14日 下午1:46:21  
 */
public interface ArticleMapper {

	/** 
	 * @Title: getNewArticleList 
	 * @Description: TODO
	 * @param count
	 * @return: void
	 * @date: 2019年11月14日下午1:50:43
	 */
	List<Article> getNewArticleList(int count);

	/** 
	 * @Title: getHotArticleList 
	 * @Description: TODO
	 * @return
	 * @return: List<Article>
	 * @date: 2019年11月14日下午2:09:34
	 */
	List<Article> getHotArticleList();

	/** 
	 * @Title: getArticleById 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: Article
	 * @date: 2019年11月14日下午6:54:27
	 */
	Article getArticleById(Integer id);

	/** 
	 * @Title: getArticleByCG 
	 * @Description: TODO
	 * @param chId
	 * @param caId
	 * @return: void
	 * @date: 2019年11月14日下午7:32:56
	 */
	List<Article> getArticleByCG(@Param("chId")int chId, @Param("caId")int caId);

	/** 
	 * @Title: getMyArticle 
	 * @Description: TODO
	 * @param id
	 * @return: void
	 * @date: 2019年11月18日下午7:12:26
	 */
	List<Article> getMyArticle(Integer id);

	/** 
	 * @Title: deleteArticle 
	 * @Description: TODO
	 * @param id
	 * @return: void
	 * @date: 2019年11月18日下午7:49:22
	 */
	@Update("update cms_article set deleted = 1 where id = ${value}")
	int deleteArticle(int id);

	/** 
	 * @Title: articleIsNull 
	 * @Description: TODO
	 * @param id
	 * @return: void
	 * @date: 2019年11月18日下午7:57:59
	 */
	Article articleIsNull(int id);

	/** 
	 * @Title: getAllArticle 
	 * @Description: TODO
	 * @param status
	 * @return: void
	 * @date: 2019年11月19日下午1:55:39
	 */
	List<Article> getAllArticle(int status);

	/** 
	 * @Title: getCheckArticleById 
	 * @Description: TODO
	 * @param id
	 * @return: void
	 * @date: 2019年11月19日下午7:16:16
	 */
	Article getCheckArticleById(int id);

	/** 
	 * @Title: checkArticleStatus 
	 * @Description: TODO
	 * @param id
	 * @param status
	 * @return: void
	 * @date: 2019年11月19日下午7:36:54
	 */
	@Update("UPDATE cms_article set status = #{status} where id = #{id}")
	int checkArticleStatus(@Param("id")int id, @Param("status")String status);

	/** 
	 * @Title: checkArticleHot 
	 * @Description: TODO
	 * @param id
	 * @param hot
	 * @return
	 * @return: int
	 * @date: 2019年11月19日下午8:09:06
	 */
	@Update("UPDATE cms_article set hot = #{hot} where id = #{id}")
	int checkArticleHot(@Param("id")int id, @Param("hot")String hot);

	/** 
	 * @Title: getArticleNotCheck 
	 * @Description: TODO
	 * @return
	 * @return: int
	 * @date: 2019年11月20日上午8:41:01
	 */
	@Select("select count(id) from cms_article where status=0 and deleted = 0")
	int getArticleNotCheck();

	/** 
	 * @Title: addArticle 
	 * @Description: 添加文章
	 * @param article
	 * @return
	 * @return: int
	 * @date: 2019年11月20日下午6:31:00
	 */
	int addArticle(Article article);

	/** 
	 * @Title: updateArticle 
	 * @Description: TODO
	 * @param article
	 * @return
	 * @return: int
	 * @date: 2019年11月20日下午8:17:49
	 */
	int updateArticle(Article article);

	/** 
	 * @Title: updateArticleCommentCntUp 
	 * @Description: TODO
	 * @return: void
	 * @date: 2019年11月21日下午1:06:56
	 */
	@Update("update cms_article set commentCnt = commentCnt + 1 where id = #{articleId}")
	int updateArticleCommentCntUp(Comment comment);

	/** 
	 * @Title: updateArticleCommentCntHitsUp 
	 * @Description: TODO
	 * @param id
	 * @return: void
	 * @date: 2019年11月21日下午3:38:44
	 */
	@Update("update cms_article set hits = hits + 1 where id = ${value}")
	int updateArticleCommentCntHitsUp(Integer id);

	/** 
	 * @Title: addImages 
	 * @Description: TODO
	 * @param article
	 * @return
	 * @return: int
	 * @date: 2019年11月22日下午1:19:23
	 */
	int addImages(Article article);

	/** 
	 * @Title: favoriteArticle 
	 * @Description: TODO
	 * @param userId
	 * @param id
	 * @return
	 * @return: int
	 * @date: 2019年11月22日下午1:46:37
	 */
	@Insert("replace cms_favorite set userId=#{userId},articleId=#{id}")
	int favoriteArticle(@Param("userId")Integer userId, @Param("id")int id);

	/** 
	 * @Title: getmyFavorite 
	 * @Description: TODO
	 * @param id
	 * @return: void
	 * @date: 2019年11月22日下午6:24:19
	 */
	List<Article> getmyFavorite(Integer id);

	/** 
	 * @Title: deleteFavorite 
	 * @Description: TODO
	 * @param id
	 * @param articleId
	 * @return
	 * @return: int
	 * @date: 2019年11月22日下午7:10:16
	 */
	@Delete("delete from cms_favorite where userId=#{id} and articleId=#{articleId}")
	int deleteFavorite(@Param("id")Integer id, @Param("articleId")int articleId);

	/** 
	 * @Title: getAllArticle 
	 * @Description: TODO
	 * @return
	 * @return: List<Article>
	 */
	@Select("select * from cms_article where deleted = 0 and status=1")
	@ResultMap("ArticleMap")
	List<Article> getAll();


}	
