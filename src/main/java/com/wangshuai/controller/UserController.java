package com.wangshuai.controller;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wangshuai.common.CmsAssert;
import com.wangshuai.common.ConstantClass;
import com.wangshuai.common.JsonMsg;
import com.wangshuai.entity.User;
import com.wangshuai.service.UserService;
import com.wangshuai.ssmutils.CaptchaUtil;

/** 
 * @ClassName: UserController 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年11月13日 上午10:38:33  
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	/**
	 * 
	 * @Title: getUserList 
	 * @Description: 获取用户集合,分页,按照姓名模糊查询
	 * @param m
	 * @param page
	 * @param name
	 * @return
	 * @return: String
	 * @date: 2019年11月13日上午11:29:46
	 */
	@RequestMapping("users")
	public String getUserList(Model m,
			@RequestParam(defaultValue = "1")String page,
			@RequestParam(defaultValue = "")String name) {
		PageInfo<User> info = service.getUserList(name,page);
		m.addAttribute("info", info);
		m.addAttribute("name", name);
		return "user/userList";
	}
	
	/**
	 * 
	 * @Title: updateUserLocked 
	 * @Description: 修改用户的封禁状态
	 * @param id
	 * @param locked
	 * @return
	 * @return: JsonMsg
	 * @date: 2019年11月15日下午1:05:27
	 */
	@ResponseBody
	@PostMapping("/updateUserLocked")
	public JsonMsg updateUserLocked(int id,int locked) {
		if (locked != 1 && locked != 0) {
			return JsonMsg.error().addInfo("update_status_error", "状态参数无效");
		}
		User user = service.geteUserById(id);
		if (user == null) {
			return JsonMsg.error().addInfo("update_status_error", "该用户不存在");
		}else if(user.getLocked() == locked){
			return JsonMsg.error().addInfo("update_status_error", "状态无需更改");
		}
		
		boolean updateUserLocked = service.updateUserLocked(id,locked);
		if (updateUserLocked) {
			return JsonMsg.success();
		}else {
			return JsonMsg.error().addInfo("update_status_error", "服务端发生异常,修改失败,请联系管理员");
		}
	}
	
	/**
	 * 
	 * @Title: register 
	 * @Description: 用户的注册
	 * @param user
	 * @return
	 * @return: JsonMsg
	 * @date: 2019年11月15日下午1:50:45
	 */
	@ResponseBody
	@PostMapping("/register")
	public JsonMsg register(User user) {
		return service.register(user);
	}

	/**
	 * 
	 * @Title: toRegister 
	 * @Description: 去注册页面
	 * @return
	 * @return: String
	 * @date: 2019年11月17日下午7:59:56
	 */
	@GetMapping("/toRegister")
	public String toRegister() {
		return "/user/register";
	}
	
	
	
	/**
	 * 
	 * @Title: goLogin 
	 * @Description: 登入
	 * @param session
	 * @param user
	 * @return
	 * @return: JsonMsg
	 * @date: 2019年11月17日下午8:00:29
	 */
	@ResponseBody
	@PostMapping("/goLogin")
	public JsonMsg goLogin(HttpSession session,User user,String code) {
		String code2 = (String) session.getAttribute("code");
		CmsAssert.AssertTrue(code2.equalsIgnoreCase(code), "验证码输入错误");
		User user2 = service.goLogin(user);
		user.setPassword(null);
		User user3 = service.goLogin(user);
		CmsAssert.AssertTrue(user3 != null, "该用户不存在");
		CmsAssert.AssertTrue(user2 != null, "密码输入错误");
		session.setAttribute(ConstantClass.USER_KEY, user2);
		if (user2.getRole() == ConstantClass.USER_ROLE_ADMIN) {
			return JsonMsg.success().addInfo("user_admin", true);
		}
		return JsonMsg.success();
	}
	/**
	 * 
	 * @Title: checkname 
	 * @Description: 验证姓名唯一
	 * @param user
	 * @return
	 * @return: boolean
	 * @date: 2019年11月17日下午8:00:49
	 */
	@ResponseBody
	@GetMapping("/checkname")
	public boolean checkname(User user) {
		return service.getUserByName(user);
	}
	
	/**
	 * 
	 * @Title: getCodeStr 
	 * @Description: 获取随机验证码
	 * @param session
	 * @param response
	 * @throws Exception
	 * @return: void
	 * @date: 2019年11月18日下午4:24:19
	 */
	@GetMapping("/getCodeStr")
	public void getCodeStr(HttpSession session,HttpServletResponse response) throws Exception {
		CaptchaUtil capt = new CaptchaUtil();
		String code = capt.getStr();
		session.setAttribute("code", code);
		ImageIO.write(capt.getImage(), "jpg", response.getOutputStream());
	}
	
	
	/**
	 * 
	 * @Title: outLogin 
	 * @Description: 退出登入
	 * @param session
	 * @return
	 * @return: String
	 * @date: 2019年11月18日下午4:27:23
	 */
	@GetMapping("/outLogin")
	public String outLogin(HttpSession session) {
		session.removeAttribute(ConstantClass.USER_KEY);
		return "redirect:/index";
	}
	
	/**
	 * 
	 * @Title: toMyCenter 
	 * @Description: 去个人中心
	 * @return
	 * @return: String
	 * @date: 2019年11月18日下午6:50:46
	 */
	@GetMapping("/myCenter")
	public String toMyCenter() {
		return "user/myCenter";
	}
	
	
	
	
	
}
