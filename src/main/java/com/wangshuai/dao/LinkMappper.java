package com.wangshuai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.wangshuai.entity.Link;

/** 
 * @ClassName: LinkMappper 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年11月23日 下午6:35:49  
 */
public interface LinkMappper {

	/** 
	 * @Title: addLink 
	 * @Description: TODO
	 * @param link
	 * @return
	 * @return: int
	 * @date: 2019年11月23日下午6:52:53
	 */
	@Insert("insert into cms_favorite set url=#{url},text=#{text},created=now(),user_id=#{userId}")
	int addLink(Link link);

	/** 
	 * @Title: getLinks 
	 * @Description: TODO
	 * @return: void
	 * @date: 2019年11月23日下午7:11:50
	 */
	@Select("select id,url,text,created,user_id from cms_favorite order by created desc")
	List<Link> getLinks();

	/** 
	 * @Title: deleteLink 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: int
	 * @date: 2019年11月25日下午8:19:46
	 */
	@Delete("delete from cms_favorite where id = #{id}")
	int deleteLink(int id);

	/** 
	 * @Title: getmyFavorite 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: List<Link>
	 * @date: 2019年11月27日上午9:10:11
	 */
	@Select("select id,url,text,created,user_id from cms_favorite where user_id=${value} order by created desc")
	List<Link> getmyFavorite(Integer id);

}
