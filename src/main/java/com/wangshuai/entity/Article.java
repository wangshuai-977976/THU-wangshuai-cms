package com.wangshuai.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/** 
 * @ClassName: Article 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年11月14日 下午1:32:38  
 */
//indexName就是制定索引库的名称   需要全部小写
@Document(indexName = "cms_article",type = "article")
public class Article implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 6927479125403981315L;
	@Id
	private Integer id;
	
	// 文章标题
	//analyzer指定分词器 index是否索引  store是否存储  searchAnalyzer搜索关键词也使用分词器
	@Field(analyzer ="ik_smart",index = true,store =true,searchAnalyzer ="ik_smart",type =FieldType.text)
	private String title;
	// 文章内容
	@Field(analyzer ="ik_smart",index = true,store =true,searchAnalyzer ="ik_smart",type =FieldType.text)
	private String content;
	//标题图片的url 地址
	private String picture;
	// 频道
	private String channelId;
	@JsonIgnore
	private Channel channel;
	
	//频道下的分类
	
	private String categoryId;
	@JsonIgnore
	private Category category;
	
	//用户
	private String userId;
	@JsonIgnore
	private User user;
	
	//点击数
	private String hits;
	//是否热门
	private String hot;
//	是否审核
	private String status;
//	是否被删除
	private String deleted;
	//发表时间
	private Date created;
//	最后修改时间
	private Date updated;
//	评论的数量
	private int commentCnt;
//	文章类型
	@JsonIgnore
	private ArticleType articleType = ArticleType.HTML;
//	文章获赞的个数
	private int likeNum;
	@JsonIgnore
	private List<Images> images;
	
	public int getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getHits() {
		return hits;
	}

	public void setHits(String hits) {
		this.hits = hits;
	}

	public String getHot() {
		return hot;
	}

	public void setHot(String hot) {
		this.hot = hot;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public int getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}

	public ArticleType getArticleType() {
		return articleType;
	}

	public void setArticleType(ArticleType articleType) {
		this.articleType = articleType;
	}

	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images) {
		this.images = images;
	}

	public Article(Integer id, String title, String content, String picture, String channelId, Channel channel,
			String categoryId, Category category, String userId, User user, String hits, String hot, String status,
			String deleted, Date created, Date updated, int commentCnt, ArticleType articleType, List<Images> images) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.picture = picture;
		this.channelId = channelId;
		this.channel = channel;
		this.categoryId = categoryId;
		this.category = category;
		this.userId = userId;
		this.user = user;
		this.hits = hits;
		this.hot = hot;
		this.status = status;
		this.deleted = deleted;
		this.created = created;
		this.updated = updated;
		this.commentCnt = commentCnt;
		this.articleType = articleType;
		this.images = images;
	}

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content=" + content + ", picture=" + picture
				+ ", channelId=" + channelId + ", channel=" + channel + ", categoryId=" + categoryId + ", category="
				+ category + ", userId=" + userId + ", user=" + user + ", hits=" + hits + ", hot=" + hot + ", status="
				+ status + ", deleted=" + deleted + ", created=" + created + ", updated=" + updated + ", commentCnt="
				+ commentCnt + ", articleType=" + articleType + ", images=" + images + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((channelId == null) ? 0 : channelId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Article other = (Article) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (channelId == null) {
			if (other.channelId != null)
				return false;
		} else if (!channelId.equals(other.channelId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
