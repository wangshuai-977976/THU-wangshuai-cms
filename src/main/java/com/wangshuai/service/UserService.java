package com.wangshuai.service;

import com.github.pagehelper.PageInfo;
import com.wangshuai.common.JsonMsg;
import com.wangshuai.entity.User;

/** 
 * @ClassName: UserService 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年11月13日 上午11:17:57  
 */
public interface UserService {

	/** 
	 * @Title: getUserList 
	 * @Description: 获取用户集合
	 * @param name
	 * @param page
	 * @return: void
	 * @date: 2019年11月13日上午11:21:04
	 */
	PageInfo<User> getUserList(String name, String page);

	/** 
	 * @Title: geteUserById 
	 * @Description: 根据id查找用户
	 * @param id
	 * @return: void
	 * @date: 2019年11月13日下午2:16:56
	 */
	User geteUserById(int id);

	/** 
	 * @Title: updateUserLocked 
	 * @Description: TODO
	 * @param id
	 * @param locked
	 * @return: void
	 * @date: 2019年11月13日下午2:24:25
	 */
	boolean updateUserLocked(int id, int locked);

	/** 
	 * @Title: register 
	 * @Description: TODO
	 * @param user
	 * @return: void
	 * @date: 2019年11月15日下午1:08:14
	 */
	JsonMsg register(User user);

	/** 
	 * @Title: goLogin 
	 * @Description: TODO
	 * @param user
	 * @return: void
	 * @date: 2019年11月15日下午4:03:51
	 */
	User goLogin(User user);

	/** 
	 * @Title: getUserByName 
	 * @Description: TODO
	 * @param user
	 * @return
	 * @return: boolean
	 * @date: 2019年11月17日下午7:43:32
	 */
	boolean getUserByName(User user);


}
