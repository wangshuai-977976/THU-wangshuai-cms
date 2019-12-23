package com.wangshuai.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/** 
 * @ClassName: Comment 
 * @Description: TODO
 * @author:王帅 
 * @date: 2019年11月21日 上午9:40:29  
 */
public class Comment implements Serializable{
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 3901393848859414124L;
	private Integer id;
	private int articleId;
	private Article article;
	private int userId;
	private User user;
	private String content;
	private Date created;
	//显示该评论的时间
	private String dateDesc;
	
	private List<Reply> replys;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getDateDesc() {
		return dateDesc;
	}

	public void setDateDesc(String dateDesc) {
		this.dateDesc = dateDesc;
	}

	public List<Reply> getReplys() {
		return replys;
	}

	public void setReplys(List<Reply> replys) {
		this.replys = replys;
	}

	public Comment(Integer id, int articleId, Article article, int userId, User user, String content, Date created,
			String dateDesc, List<Reply> replys) {
		super();
		this.id = id;
		this.articleId = articleId;
		this.article = article;
		this.userId = userId;
		this.user = user;
		this.content = content;
		this.created = created;
		this.dateDesc = dateDesc;
		this.replys = replys;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", articleId=" + articleId + ", article=" + article + ", userId=" + userId
				+ ", user=" + user + ", content=" + content + ", created=" + created + ", dateDesc=" + dateDesc
				+ ", replys=" + replys + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + articleId;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (articleId != other.articleId)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	

	
	
	
	
	
	

}
