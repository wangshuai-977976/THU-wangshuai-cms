package com.wangshuai.common;

import org.apache.commons.codec.digest.DigestUtils;

/** 
 * @ClassName: Md5 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年11月15日 下午1:41:38  
 */
public class Md5 {

	public static String password(String password,String salt) {
		return DigestUtils.md5Hex(password+"王帅 "+salt);
	}
	
}
