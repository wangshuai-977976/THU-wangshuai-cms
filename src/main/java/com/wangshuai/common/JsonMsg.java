package com.wangshuai.common;

import java.util.HashMap;
import java.util.Map;

/** 
 * @ClassName: JsonMsg 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年11月13日 下午1:53:16  
 */
public class JsonMsg {

	private int status;
	private String msg;
	private Map<String,Object> info = new HashMap();
	
	
	

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the info
	 */
	public Map<String, Object> getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(Map<String, Object> info) {
		this.info = info;
	}

	/**
	 * 
	 * @Title: error 
	 * @Description: 操作成功的信息
	 * @return
	 * @return: JsonMsg
	 * @date: 2019年11月13日下午2:00:34
	 */
	public static JsonMsg error() {
		JsonMsg jsonMsg = new JsonMsg();
		jsonMsg.setStatus(200);
		jsonMsg.setMsg("操作失败");
		return jsonMsg;
	}
	
	/**
	 * 
	 * @Title: success 
	 * @Description: 操作失败的信息
	 * @return
	 * @return: JsonMsg
	 * @date: 2019年11月13日下午2:01:25
	 */
	public static JsonMsg success() {
		JsonMsg jsonMsg = new JsonMsg();
		jsonMsg.setStatus(100);
		jsonMsg.setMsg("操作成功");
		return jsonMsg;
	}
	
	/**
	 * 
	 * @Title: addInfo 
	 * @Description: 添加其他的信息
	 * @param key
	 * @param value
	 * @return
	 * @return: JsonMsg
	 * @date: 2019年11月13日下午2:04:35
	 */
	public JsonMsg addInfo(String key,Object value) {
		this.info.put(key,value);
		return this;
	}
	
	
	
	
}
