package com.wangshuai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wangshuai.entity.User;

/** 
 * @ClassName: UserMapper 
 * @Description: TODO
 * @author:王帅 
 * @date: 2019年11月13日 上午11:17:43  
 */
public interface UserMapper {

	/** 
	 * @Title: getUserList 
	 * @Description: TODO
	 * @param name
	 * @return: void
	 * @date: 2019年11月13日上午11:22:59
	 */
	List<User> getUserList(String name);

	/** 
	 * @Title: getUserById 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: User
	 * @date: 2019年11月13日下午2:17:48
	 */
	User getUserById(int id);

	/** 
	 * @Title: updateUserLocked 
	 * @Description: TODO
	 * @param id
	 * @param locked
	 * @return
	 * @return: boolean
	 * @date: 2019年11月13日下午2:27:02
	 */
	@Update("UPDATE cms_user SET locked=${locked} WHERE id =${id}")
	int updateUserLocked(@Param("id")int id, @Param("locked")int locked);

	/** 
	 * @Title: getUserByName 
	 * @Description: TODO
	 * @param username
	 * @return: void
	 * @date: 2019年11月15日下午1:09:05
	 */
	@Select("select id from cms_user where username = #{value} limit 1")
	User getUserByName(String username);

	/** 
	 * @Title: register 
	 * @Description: TODO
	 * @param user
	 * @return: void
	 * @date: 2019年11月15日下午1:35:39
	 */
	int register(User user);

	/** 
	 * @Title: goLogin 
	 * @Description: TODO
	 * @param user
	 * @return: void
	 * @date: 2019年11月15日下午4:06:53
	 */
	User goLogin(User user);

}
