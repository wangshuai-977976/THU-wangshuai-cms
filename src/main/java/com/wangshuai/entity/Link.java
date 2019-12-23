package com.wangshuai.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

/** 
 * @ClassName: Link 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年11月23日 下午6:17:41  
 */
public class Link implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 6767027834726433812L;
	private Integer id;
	
	@NotBlank(message = "不可为空")
	private String text;
	@NotBlank(message = "不可为空")
	@URL(message = "url格式不符合")
	private String url;
	private int userId;
	private Date created;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Link(Integer id, String text, String url, int userId, Date created) {
		super();
		this.id = id;
		this.text = text;
		this.url = url;
		this.userId = userId;
		this.created = created;
	}
	public Link() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Link [id=" + id + ", text=" + text + ", url=" + url + ", userId=" + userId + ", created=" + created
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		Link other = (Link) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
	
	
	
	

}
