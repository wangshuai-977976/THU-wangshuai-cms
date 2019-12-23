package com.wangshuai.dao;

import com.wangshuai.entity.Reply;

/** 
 * @ClassName: ReplyMapper 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年11月26日 上午9:12:03  
 */
public interface ReplyMapper {

	/** 
	 * @Title: pushReply 
	 * @Description: 发表回复
	 * @param reply
	 * @return: void
	 * @date: 2019年11月26日上午9:12:17
	 */
	void pushReply(Reply reply);

}
