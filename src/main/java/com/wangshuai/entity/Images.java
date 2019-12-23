package com.wangshuai.entity;

import java.io.Serializable;

/** 
 * @ClassName: Images 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年11月22日 下午1:14:23  
 */
public class Images implements Serializable {

	private String url;
	
	private String desc;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Images(String url, String desc) {
		super();
		this.url = url;
		this.desc = desc;
	}

	public Images() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Images [url=" + url + ", desc=" + desc + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		Images other = (Images) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	
}
