package com.wangshuai.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wangshuai.common.CmsAssert;
import com.wangshuai.common.ConstantClass;
import com.wangshuai.common.JsonMsg;
import com.wangshuai.entity.Article;
import com.wangshuai.entity.Comment;
import com.wangshuai.entity.Reply;
import com.wangshuai.entity.User;
import com.wangshuai.service.ArticleService;
import com.wangshuai.service.CommentService;

/** 
 * @ClassName: CommentController 
 * @Description: 处理评论
 * @author:王帅  
 * @date: 2019年11月21日 下午3:02:01  
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	//文章
	@Autowired
	private ArticleService articleService;
	
	Logger log = Logger.getLogger(CommentController.class);


	/**
	 * 
	 * @Title: likeComment 
	 * @Description: 用户点赞这个评论
	 * @param id
	 * @return
	 * @return: JsonMsg
	 * @date: 2019年11月21日下午3:04:07
	 */
	@ResponseBody
	@PostMapping("/likeComment")
	public JsonMsg likeComment(int id) {
		if(commentService.likeComment(id) > 0)
			return JsonMsg.success();
		else
			return JsonMsg.error();
	}
	
	/**
	 * 
	 * @Title: pushComment 
	 * @Description: 发布文章评论
	 * @param session
	 * @param comment
	 * @return
	 * @return: JsonMsg
	 * @date: 2019年11月21日下午4:09:50
	 */
	@ResponseBody
	@PostMapping("/pushComment")
	public JsonMsg pushComment(HttpSession session,Comment comment) {
		User user = (User) session.getAttribute(ConstantClass.USER_KEY);
		comment.setUserId(user.getId());
		
		Article article = articleService.articleIsNull(comment.getArticleId());
		CmsAssert.AssertTrue(article != null , "文章不存在");
		int result = commentService.pushComment(comment);
		if (result > 0) return JsonMsg.success();
		else return JsonMsg.error();
	}
	
	/**
	 * 
	 * @Title: myComment 
	 * @Description: 回去我的评论
	 * @param m
	 * @param session
	 * @param page
	 * @return
	 * @return: String
	 * @date: 2019年11月26日上午8:58:39
	 */
	@GetMapping("/myComment")
	public String myComment(Model m,HttpSession session,
							@RequestParam(defaultValue = "1")int page) {
		User user = (User)session.getAttribute(ConstantClass.USER_KEY);
		if (user != null) {
			PageInfo<Comment> info = commentService.getMyComment(page,user.getId());
			m.addAttribute("info", info);
		}
		return "user/myComment";
	}
	
	@ResponseBody
	@PostMapping("/pushReply")
	public JsonMsg pushReply(HttpSession session,Reply reply) {
		User user = (User) session.getAttribute(ConstantClass.USER_KEY);
		reply.setFromUserId(user.getId());
		commentService.pushReply(reply);
		return JsonMsg.success();
	}
	
	

}
