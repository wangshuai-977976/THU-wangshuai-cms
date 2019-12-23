package com.wangshuai.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangshuai.dao.ArticleMapper;
import com.wangshuai.dao.CommentMapper;
import com.wangshuai.dao.ReplyMapper;
import com.wangshuai.dateUtils.DateDesc;
import com.wangshuai.entity.Comment;
import com.wangshuai.entity.Reply;
import com.wangshuai.service.CommentService;

/** 
 * @ClassName: CommentServiceImpl 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年11月21日 上午9:49:02  
 */
@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentMapper mapper;

	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private ReplyMapper replyMapper;
	
	@Override
	public PageInfo<Comment> getArticleCommentById(Integer articleId,int pageNum) throws Exception {
		PageHelper.startPage(pageNum, 5);
		List<Comment> list = mapper.getArticleCommentById(articleId);
		//改变评论的恢复时间,使用倒计时
		for (Comment comment : list) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:m:s");
			Date date = format.parse(comment.getDateDesc());
			comment.setDateDesc(DateDesc.format(date));
			System.out.println(comment);
		}
		for (Comment comment : list) {
			for (Reply repl : comment.getReplys()) {
				String descDate = DateDesc.format(repl.getCreated());
				repl.setDescDate(descDate);
			}
		}
		return new PageInfo<Comment>(list);
	}

	@Override
	public int pushComment(Comment comment) {
		//文章的评论数量加1
		articleMapper.updateArticleCommentCntUp(comment);
		return mapper.pushComment(comment);
	}

	@Override
	public int likeComment(int id) {
		return mapper.likeComment(id);
	}

	@Override
	public PageInfo<Comment> getMyComment(int page,Integer id) {
		PageHelper.startPage(page, 10);
		List<Comment> list = mapper.getMyComment(id);
		return new PageInfo<Comment>(list);
	}

	@Override
	public void pushReply(Reply reply) {
		replyMapper.pushReply(reply);
	}
	
}
