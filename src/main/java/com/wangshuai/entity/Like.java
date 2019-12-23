package com.wangshuai.entity;

import java.io.Serializable;

/** 
 * @ClassName: Like 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年11月26日 下午1:15:38  
 */
public class Like implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -1471384921042676997L;
	private Integer id;
	private int typeId;
	private int type;
	private int userId;
	private int status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Like(Integer id, int typeId, int type, int userId, int status) {
		super();
		this.id = id;
		this.typeId = typeId;
		this.type = type;
		this.userId = userId;
		this.status = status;
	}
	public Like() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Like [id=" + id + ", typeId=" + typeId + ", type=" + type + ", userId=" + userId + ", status=" + status
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + status;
		result = prime * result + type;
		result = prime * result + typeId;
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
		Like other = (Like) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status != other.status)
			return false;
		if (type != other.type)
			return false;
		if (typeId != other.typeId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
	

}
