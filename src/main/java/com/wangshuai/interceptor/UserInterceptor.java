package com.wangshuai.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wangshuai.common.CmsAssert;
import com.wangshuai.common.ConstantClass;
import com.wangshuai.entity.User;

/** 
 * @ClassName: UserInterceptor 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年11月18日 下午3:55:52  
 */
public class UserInterceptor implements HandlerInterceptor{

	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取请求头X-Requested-With判断是否为ajax请求
		String type = request.getHeader("X-Requested-With") == null ? "" : request.getHeader("X-Requested-With");
		User user = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
		if (user == null) {
			if ("XMLHttpRequest".equals(type)) {
				 //处理AJAX请求，设置响应头信息
	            response.setHeader("REDIRECT", "REDIRECT");
	            /*需要跳转页面的URL*/
	            response.setHeader("CONTEXTPATH", request.getContextPath()+"/login");
			}else {
				response.sendRedirect("/login?choose=login");
			}
			return false;
		}
		if (request.getServletPath().contains("admin") && user.getRole() != ConstantClass.USER_ROLE_ADMIN) {
			request.setAttribute("error", "只有管理员可访问");
			request.getRequestDispatcher("error").forward(request, response);
		}
		return true;
	}
	
	
}
