package com.wangshuai.interceptor;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wangshuai.common.CmsException;
import com.wangshuai.common.CmsExceptionHtml;
import com.wangshuai.common.JsonMsg;

/** 
 * @ClassName: ControllerInterceptor 
 * @Description: controller的切面
 * @author:王帅  
 * @date: 2019年11月19日 下午1:02:18  
 */
@ControllerAdvice
public class ControllerInterceptor {

	
	/**
	 * 
	 * @Title: interceptorController 
	 * @Description: 拦截报错信息,将报错信息已json数据返回前端
	 * @param excetion
	 * @return
	 * @return: JsonMsg
	 * @date: 2019年11月19日下午1:37:12
	 */
	@ExceptionHandler(CmsException.class)
	@ResponseBody
	public JsonMsg 	interceptorController(CmsException excetion) {
		System.out.println("错误是"+excetion);
		return JsonMsg.error().addInfo("error", excetion.getMessage());
	}
	
	
	/**
	 * 
	 * @Title: interceptorController 
	 * @Description: 拦截报错信息,将报错信息返回到页面
	 * @param excetion
	 * @return
	 * @return: ModelAndView
	 * @date: 2019年11月19日下午1:37:57
	 */
	@ExceptionHandler(CmsExceptionHtml.class)
	@ResponseBody
	public ModelAndView interceptorController(CmsExceptionHtml excetion) {
		System.out.println("错误是"+excetion);
		return new ModelAndView("/error").addObject("error", excetion.getMessage());
	}
	
	
	
	
	
	
	
	
}
