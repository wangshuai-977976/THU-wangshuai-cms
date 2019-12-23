package com.wangshuai.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wangshuai.common.CmsAssert;
import com.wangshuai.common.ConstantClass;
import com.wangshuai.common.JsonMsg;
import com.wangshuai.entity.Link;
import com.wangshuai.entity.User;
import com.wangshuai.service.LinkService;

/** 
 * @ClassName: LinkController 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年11月23日 下午6:33:00  
 */
@RequestMapping("link")
@Controller
public class LinkController {
	
	@Autowired
	private LinkService linkService;
	
	
	/**
	 * 
	 * @Title: addLink 
	 * @Description: 去往添加友情链接页面
	 * @param m
	 * @return
	 * @return: String
	 * @date: 2019年11月23日下午7:09:08
	 */
	@GetMapping("/addLink")
	public String addLink(Model m) {
		m.addAttribute("link", new Link());
		return "system/addLink2";
	}
	@GetMapping("/addUserLink")
	public String addUserLink(Model m) {
		m.addAttribute("link", new Link());
		return "user/addLink2";
	}
	
	/**
	 * 
	 * @Title: addLink 
	 * @Description: 添加友情链接
	 * @param link
	 * @param result
	 * @return
	 * @return: String
	 * @date: 2019年11月23日下午7:09:26
	 */
	@PostMapping("/addLink")
	public String addLink(@Valid @ModelAttribute("link")Link link,BindingResult result,
							HttpSession session) {
		if (result.hasErrors()) {
				return "system/addLink2";
			
		}
		User user = (User) session.getAttribute(ConstantClass.USER_KEY);
		link.setUserId(user.getId());
		linkService.addLink(link);
		return "system/index";
	}
	@PostMapping("/addUserLink")
	public String addUserLink(@Valid @ModelAttribute("link")Link link,BindingResult result,
			HttpSession session) {
		if (result.hasErrors()) {
			return "user/addLink2";
			
		}
		User user = (User) session.getAttribute(ConstantClass.USER_KEY);
		link.setUserId(user.getId());
		linkService.addLink(link);
		return "user/myCenter";
	}
	
	/**
	 * 
	 * @Title: linkList 
	 * @Description: 获取友情链接的集合
	 * @param m
	 * @param page
	 * @return
	 * @return: String
	 * @date: 2019年11月25日下午8:16:17
	 */
	@GetMapping("/linkList")
	public String linkList(Model m,@RequestParam(defaultValue = "1")int page) {
		PageInfo<Link> info = linkService.getLinks(page);
		m.addAttribute("info", info);
		return "system/links";
	}
	
	@GetMapping("/myFavorite")
	public String myFavorite(Model m,@RequestParam(defaultValue = "1")int page,HttpSession session) {
		User user = (User) session.getAttribute(ConstantClass.USER_KEY);
		PageInfo<Link> info = linkService.myFavorite(page,user.getId());
		m.addAttribute("info", info);
		return "user/links";
	}

	/**
	 * 
	 * @Title: deleteLink 
	 * @Description: 删除友情链接
	 * @param session
	 * @param id
	 * @return
	 * @return: JsonMsg
	 * @date: 2019年11月25日下午8:21:24
	 */
	@ResponseBody
	@PostMapping("/deleteLink")
	public JsonMsg deleteLink(HttpSession session,int id) {
		int result = linkService.deleteLink(id);
		if (result > 0) {
			return JsonMsg.success();
		}else {
			return JsonMsg.error();
		}
	}

}
