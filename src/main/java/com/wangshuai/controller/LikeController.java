package com.wangshuai.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangshuai.common.ConstantClass;
import com.wangshuai.common.JsonMsg;
import com.wangshuai.entity.Like;
import com.wangshuai.entity.User;
import com.wangshuai.service.LikeService;

/** 
 * @ClassName: LikeController 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年11月26日 下午1:09:42  
 */
@Controller
@RequestMapping("/like")
public class LikeController {
	
	@Autowired
	private LikeService likeService;
	
	@ResponseBody
	@PostMapping("/insertLike")
	public JsonMsg likeArticle(HttpSession session,Like like) {
		User user = (User) session.getAttribute(ConstantClass.USER_KEY);
		like.setUserId(user.getId());
		likeService.insertLike(like);
		return JsonMsg.success();
	}

}
