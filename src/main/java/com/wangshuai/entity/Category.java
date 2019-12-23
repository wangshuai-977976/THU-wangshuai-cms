package com.wangshuai.entity;

import java.io.Serializable;

/** 
 * @ClassName: Category 
 * @Description: 频道下的栏目
 * @author:王帅  
 * @date: 2019年11月14日 下午1:36:10  
 */
public class Category implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -9196092774377934351L;
	private Integer id;
	private String name;
	private int channeId;
	private Channel channel;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getChanneId() {
		return channeId;
	}
	public void setChanneId(int channeId) {
		this.channeId = channeId;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public Category(Integer id, String name, int channeId, Channel channel) {
		super();
		this.id = id;
		this.name = name;
		this.channeId = channeId;
		this.channel = channel;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", channeId=" + channeId + ", channel=" + channel + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
	
	

}
