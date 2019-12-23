package com.wangshuai.common;

/**
 * 
 * @ClassName: CmsAssert 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年11月12日 下午1:05:24
 */
public class CmsAssert {

	public static void  AssertTrue(boolean express,String msg){
		if (!express) {
			throw new CmsException(msg);
		}
	}
	
	/**
	 * 
	 * @Title: AssertTrueHtml 
	 * @Description: 在页面显示的异常
	 * @param express
	 * @param msg
	 * @return: void
	 * @date: 2019年11月19日下午1:15:40
	 */
	public static void  AssertTrueHtml(boolean express,String msg){
		if (!express) {
			throw new CmsExceptionHtml(msg);
		}
	}
}
