package com.wangshuai.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.wangshuai.common.CmsAssert;
import com.wangshuai.common.ConstantClass;
import com.wangshuai.common.ESHLUtil;
import com.wangshuai.common.JsonMsg;
import com.wangshuai.dao.ArticleResp;
import com.wangshuai.entity.Article;
import com.wangshuai.entity.ArticleType;
import com.wangshuai.entity.Category;
import com.wangshuai.entity.Channel;
import com.wangshuai.entity.Comment;
import com.wangshuai.entity.Images;
import com.wangshuai.entity.User;
import com.wangshuai.service.ArticleService;
import com.wangshuai.service.CategoryService;
import com.wangshuai.service.ChannelService;
import com.wangshuai.service.CommentService;
/*import com.wangshuai.dateUtils.DateDesc;
import com.wangshuai.fileUtils.FileUtil;*/

/** 
 * @ClassName: ArticleController 
 * @Description: TODO
 * @author:王帅  
 * @date: 2019年11月14日 下午6:51:15  
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
	
	//文章
	@Autowired
	private ArticleService articleService;
	
	//分类
	@Autowired
	private CategoryService categoryService; 
	
	//频道
	@Autowired
	private ChannelService channelService;
	
	//评论
	@Autowired
	private CommentService commentService;
	
	//es仓库
	@Autowired
	ArticleResp articleResp;
	//kafka
	@Autowired
	KafkaTemplate<String,String> kafkaTemplate;
	@Autowired
	RedisTemplate redisTemplate;
	@Autowired
	ThreadPoolTaskExecutor executor;
	/**
	 * 日期格式工具
	 */
	private SimpleDateFormat sdf;
	/**
	 * 
	 * @Title: getArticleById 
	 * @Description: 根据id获取文章
	 * @param m
	 * @param id
	 * @return
	 * @return: String
	 * @throws Exception 
	 * @date: 2019年11月14日下午7:28:58
	 */
	@GetMapping("/getArticleById")
	public String getArticleById(Model m,HttpServletRequest request,Integer id,String protal,String page,
								@RequestParam(defaultValue = "1")int pageNum,
								String scrollTo) throws Exception {

		
		System.out.println("protal=========================="+protal);
		if (scrollTo != null) {
			m.addAttribute("scrollTo", scrollTo);
		}
		//这里是获取那个页面点击的进入详情,之后的上一章下一张就是按照这个换的
		List<Article> list =null;
		if (protal != null) {
			if (ConstantClass.PROTAL_HOT.equals(protal)) {
				list = articleService.getHotArticleList(0).getList();
			}else if (ConstantClass.PROTAL_NEW.equals(protal)) {
				list = articleService.getNewArticleList(0);
			}else if (Pattern.matches("\\d+,\\d+", protal)) {
				String[] split = protal.split(",");
				list = articleService.getArticleByCG(Integer.parseInt(split[0]), Integer.parseInt(split[1]),0).getList();
			}else {
				list = articleService.getHotArticleList(0).getList();
				System.out.println("错误");
			}
		}
		//这里是处理上一张下一张
		Integer articleId = null;
		if ("next".equals(page)) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getId());
				if (list.get(i).getId().equals(id)) {
					if (i == (list.size() - 1)) {
						i=-1;
					}
					articleId = list.get(++i).getId();
					break;
				}
			}
		}else if("pre".equals(page)){
			for (int i = list.size() - 1; i >= 0; i--) {
				if (list.get(i).getId().equals(id)) {
					if (i == 0) {
						i=list.size();
					}
					articleId = list.get(--i).getId();
					break;
				}
			}
		}else {
			//第一次进入
			articleId = id;
		}
		System.out.println(articleId+"=============================");
//		获取文章
		Article article = articleService.getArticleById(articleId);
		
		
		/**
		 * 现在请你利用Redis提高性能，当用户浏览文章时，
		 * 将“Hits_${文章ID}_${用户IP地址}”为key，
		 * 查询Redis里有没有该key，如果有key，则不做任何操作。
		 * 如果没有，则使用Spring线程池异步执行数据库加1操作，
		 * 并往Redis保存key为Hits_${文章ID}_${用户IP地址}，
		 * value为空值的记录，而且有效时长为5分钟。
		 */
		//获取访问用户的ip
		String ip = request.getRemoteAddr();
		String key = "Hits_"+id+"_"+ip;
		//判断redis是否存在该键
		String redisData = (String) redisTemplate.opsForValue().get(key);
		if(redisData==null) {
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					article.setHits(article.getHits()+1);
					redisTemplate.opsForValue().set(key,"",5 ,TimeUnit.MINUTES);
					System.out.println("点击量+1===================");
					articleService.updateArticle(article);
				}
			});
		}
		
		
//		文章评论
		PageInfo<Comment> info = commentService.getArticleCommentById(articleId,pageNum);
		m.addAttribute("info", info);
		
		
		
		m.addAttribute("protal", protal);
		if (article.getArticleType() == ArticleType.HTML) {
			m.addAttribute("article", article);
			return "article/articleDetails";
		}else {
			//将图片信息转换为image格式
			Gson gson = new Gson();
			List images = gson.fromJson(article.getContent(), List.class);
			article.setImages(images);
			System.out.println(article);
			m.addAttribute("article", article);
			return "article/imgArticleDetails";
		}
	}
	
	
	/**
	 * 
	 * @Title: getArticleByCG 
	 * @Description: 根据频道获取文章
	 * @param m
	 * @param chId
	 * @param caId
	 * @param page
	 * @return
	 * @return: String
	 * @date: 2019年11月15日上午10:41:03
	 */
	@GetMapping("/getArticleByCG")
	public String getArticleByCG(Model m,
								 @RequestParam(defaultValue = "1")int chId,
								 @RequestParam(defaultValue = "0")int caId,
								 @RequestParam(defaultValue = "1")int page) {
		
		List<Category> cateList = categoryService.getCateListByChannelId(chId);
		m.addAttribute("cateList",cateList);
		/**
		 * 获取文章
		 */
		PageInfo<Article> info = articleService.getArticleByCG(chId,caId,page);
		List<Channel> channelList = channelService.getChannelList();
		channelList.forEach(System.out::println);
		
		m.addAttribute("channelList",channelList);
		m.addAttribute("caId",caId);
		m.addAttribute("chId",chId);
		m.addAttribute("info",info);
		return "article/channelCate2";
	}
	
	/**
	 * 
	 * @Title: myArticle 
	 * @Description: 获取我的文章
	 * @param m
	 * @param session
	 * @param page
	 * @return
	 * @return: String
	 * @date: 2019年11月19日下午1:48:33
	 */
	@GetMapping("/myArticle")
	public String myArticle(Model m,HttpSession session,@RequestParam(defaultValue = "1")String page) {
		User user = (User) session.getAttribute(ConstantClass.USER_KEY);
		if (user != null){
			PageInfo<Article> info = articleService.getMyArticle(page,user.getId());
			m.addAttribute("info", info);
		}
		return "user/myArticle";
	} 
	
	/**
	 * 
	 * @Title: deleteArticle 
	 * @Description: 删除文章
	 * @param id
	 * @param session
	 * @return
	 * @return: JsonMsg
	 * @date: 2019年11月19日下午1:48:46
	 */
	@ResponseBody
	@PostMapping("/deleteArticle")
	public JsonMsg deleteArticle(int id,HttpSession session) {
		CmsAssert.AssertTrue(id !=0 , "文章id必须大于零");
		Article article = articleService.articleIsNull(id);
		CmsAssert.AssertTrue(article != null , "文章不存在");
		User user = (User) session.getAttribute(ConstantClass.USER_KEY);
		CmsAssert.AssertTrue(article.getUser().getId() == user.getId() || user.getRole() == ConstantClass.USER_ROLE_ADMIN, "无权限删除");
		if (articleService.deleteArticle(id) > 0) {
			return JsonMsg.success();
		}else {
			return JsonMsg.error();
		}
	}

	/**
	 * 
	 * @Title: articleList 
	 * @Description: 管理员获取所有文章
	 * @param m
	 * @param status
	 * @param page
	 * @return
	 * @return: String
	 * @date: 2019年11月19日下午7:02:49
	 */
	@GetMapping("/articleList")
	public String articleList(Model m,@RequestParam(defaultValue = "2")int status,
									  @RequestParam(defaultValue = "1")int page) {
		PageInfo<Article> info = articleService.getAllArticle(status,page);
		int notCheckCount = articleService.getArticleNotCheck();
		m.addAttribute("status", status);
		m.addAttribute("info", info);
		m.addAttribute("notCheckCount", notCheckCount);
		return "article/allArticleList";
	}
	
	/**
	 * @Title: getCheckArticle 
	 * @Description: 获取审核文章
	 * @param id
	 * @return
	 * @return: JsonMsg
	 * @date: 2019年11月19日下午7:14:24
	 */
	@PostMapping("/getCheckArticle")
	@ResponseBody
	public JsonMsg getCheckArticle(int id) {
		Article article = articleService.getCheckArticleById(id);
		CmsAssert.AssertTrue(article != null, "该文章不存在");
		return JsonMsg.success().addInfo("article", article);
	}
	
	
	/**
	 * 
	 * @Title: checkArticleStatus 
	 * @Description: 修改文章的状态
	 * @param id
	 * @param status
	 * @return
	 * @return: JsonMsg
	 * @date: 2019年11月19日下午8:03:11
	 */
	@ResponseBody
	@PostMapping("/checkArticleStatus")
	public JsonMsg checkArticleStatus(int id,String status) {
		Article article = articleService.getCheckArticleById(id);
		CmsAssert.AssertTrue(article != null, "该文章不存在");
		CmsAssert.AssertTrue(!status.equals(article.getStatus()), "状态无需更该");
		articleService.checkArticleStatus(id,status);
		return JsonMsg.success();
	}
	
	/**
	 * 
	 * @Title: checkArticleHot 
	 * @Description: 设置文章的热门状态
	 * @param id
	 * @param hot
	 * @return
	 * @return: JsonMsg
	 * @date: 2019年11月20日下午1:23:58
	 */
	@ResponseBody
	@PostMapping("/checkArticleHot")
	public JsonMsg checkArticleHot(int id,String hot) {
		Article article = articleService.getCheckArticleById(id);
		CmsAssert.AssertTrue(article != null, "该文章不存在");
		System.out.println(article.getHot() + "===="+hot);
		CmsAssert.AssertTrue(!hot.equals(article.getHot()), "状态无需更该");
		articleService.checkArticleHot(id,hot);
		return JsonMsg.success();
	}
	
	/**
	 * 
	 * @Title: postArticle 
	 * @Description: 去往添加文章
	 * @param m
	 * @return
	 * @return: String
	 * @date: 2019年11月20日下午7:25:04
	 */
	@GetMapping("/postArticle")
	public String postArticle(Model m) {
		List<Channel> channelList = channelService.getChannelList();
		m.addAttribute("channelList", channelList);
		return "article/publish";
	}
	
	/**
	 * 
	 * @Title: postArticle 
	 * @Description: 添加文章
	 * @param session
	 * @param file
	 * @param article
	 * @return
	 * @return: JsonMsg
	 * @date: 2019年11月20日下午7:25:24
	 */
	@ResponseBody
	@PostMapping("/postArticle")
	public JsonMsg postArticle(HttpSession session,MultipartFile file,Article article) {
		//获取文章标题图片
		if (!file.isEmpty()) {
			String fileUrl = processFile(file);
			article.setPicture(fileUrl);
		}
		User user = (User)session.getAttribute(ConstantClass.USER_KEY);
		article.setUserId(String.valueOf(user.getId()));
		int result = articleService.addArticle(article);
		if (result >0 ) {
			return JsonMsg.success();
		}else {
			return JsonMsg.error();
		}
	}


	/** 
	 * @Title: processFile 
	 * @Description: 保存文件
	 * @param file
	 * @return 
	 * @return: void
	 * @date: 2019年11月20日下午2:39:24
	 */
	private String processFile(MultipartFile file) {
		String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
		String prefixName = UUID.randomUUID().toString();
		String fileName = prefixName + suffixName;
		sdf = new SimpleDateFormat("yyyyMMdd");
		String path = sdf.format(new Date());
		File pathFile = new File("d:/pic/"+path);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		String newFileName = "d:/pic/"+path+"/"+fileName;
		System.out.println(newFileName);
		try {
			file.transferTo(new File(newFileName));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path+"/"+fileName;
		
		
	}
	
	/**
	 * 
	 * @Title: getCategoryByChannel 
	 * @Description: 根据频道获取分类
	 * @param chId
	 * @return
	 * @return: JsonMsg
	 * @date: 2019年11月20日下午6:45:14
	 */
	@ResponseBody
	@PostMapping("/getCategoryByChannel")
	public JsonMsg getCategoryByChannel(int chId) {
		List<Category> categorys = categoryService.getCateListByChannelId(chId);
		if (categorys != null) {
			return JsonMsg.success().addInfo("categorys", categorys);
		}else {
			return JsonMsg.error().addInfo("error", "频道错误");
		}
	}
	
	/**
	 * 
	 * @Title: updateArticle 
	 * @Description: 获取文章详情去往修改页面
	 * @param m
	 * @param id
	 * @return
	 * @return: String
	 * @date: 2019年11月21日上午10:56:48
	 */
	@GetMapping("/updateArticle")
	public String updateArticle(Model m,int id) {
		List<Channel> channelList = channelService.getChannelList();
		m.addAttribute("channelList", channelList);
		Article article = articleService.getCheckArticleById(id);
		m.addAttribute("article", article);
		CmsAssert.AssertTrueHtml(article != null, "该文章不存在");
		return "article/updateArticle";
	}
	
	/**
	 * 
	 * @Title: updateArticle 
	 * @Description: 修改文章
	 * @param session
	 * @param file
	 * @param article
	 * @return
	 * @return: JsonMsg
	 * @date: 2019年11月21日上午10:57:06
	 */
	@ResponseBody
	@PostMapping("/updateArticle")
	public JsonMsg updateArticle(HttpSession session,MultipartFile file,Article article) {
		//获取文章标题图片
		if (!file.isEmpty()) {
			String fileUrl = processFile(file);
			article.setPicture(fileUrl);
		}
		User user = (User)session.getAttribute(ConstantClass.USER_KEY);
		article.setUserId(String.valueOf(user.getId()));
		int result = articleService.updateArticle(article);
		if (result >0 ) {
			return JsonMsg.success();
		}else {
			return JsonMsg.error();
		}
	}
	
	/**
	 * 
	 * @Title: postImage 
	 * @Description: 去往添加图片页面
	 * @param m
	 * @return
	 * @return: String
	 * @date: 2019年11月22日下午1:35:07
	 */
	@GetMapping("/postImage")
	public String postImage(Model m) {
		List<Channel> channelList = channelService.getChannelList();
		m.addAttribute("channelList", channelList);
		return "user/postImage";
	}
	
	
	
	/**
	 * 
	 * @Title: postImage 
	 * @Description: 添加图片集
	 * @param session
	 * @param file
	 * @param desc
	 * @param article
	 * @return
	 * @return: JsonMsg
	 * @date: 2019年11月22日下午1:35:22
	 */
	@ResponseBody
	@PostMapping("/postImage")
	public JsonMsg postImage(HttpSession session,MultipartFile[] file,String[] desc,Article article) {
		//获取文章标题图片
		List<Images> list = new ArrayList<Images>();
		for (int i = 0; i < desc.length; i++) {
			String fileUrl = processFile(file[i]);
			article.setPicture(fileUrl);
			Images images = new Images(fileUrl, desc[i]);
			list.add(images);
		}
		Gson gson = new Gson();
		String json = gson.toJson(list);
		article.setContent(json);
		User user = (User)session.getAttribute(ConstantClass.USER_KEY);
		article.setUserId(String.valueOf(user.getId()));
		int result = articleService.addImages(article);
		
		if (result >0 ) {
			return JsonMsg.success();
		}else {
			return JsonMsg.error();
		}
	}
	
	/**
	 * 
	 * @Title: favoriteArticle 
	 * @Description: 收藏文章
	 * @param id
	 * @param session
	 * @return
	 * @return: JsonMsg
	 * @date: 2019年11月22日下午3:30:48
	 */
	@ResponseBody
	@PostMapping("/favoriteArticle")
	public JsonMsg favoriteArticle(int id,HttpSession session) {
		User user = (User) session.getAttribute("user");
		Article articleIsNull = articleService.articleIsNull(id);
		CmsAssert.AssertTrue(articleIsNull != null, "该文章不存在");
		int result = articleService.favoriteArticle(user.getId(),id);
		if (result > 0) {
			return JsonMsg.success();
		}else {
			return JsonMsg.error().addInfo("error", "收藏失败");
		}
	}
	
	/**
	 * 
	 * @Title: myFavorite 
	 * @Description: 获取到我的收藏
	 * @param session
	 * @param page
	 * @param m
	 * @return
	 * @return: String
	 * @date: 2019年11月22日下午7:08:29
	 */
	@GetMapping("/myFavorite")
	public String myFavorite(HttpSession session,
							@RequestParam(defaultValue = "1")int page,Model m) {
		User user = (User) session.getAttribute(ConstantClass.USER_KEY);
		CmsAssert.AssertTrueHtml(user != null, "请去登入");
		PageInfo<Article> info = articleService.getmyFavorite(user.getId(),page);
		m.addAttribute("info", info);
		return "user/myFavorite";
	}
	
	/**
	 * 
	 * @Title: deleteFavorite 
	 * @Description: 删除收藏的文章
	 * @param session
	 * @param id
	 * @return
	 * @return: JsonMsg
	 * @date: 2019年11月26日下午1:06:38
	 */
	@ResponseBody
	@PostMapping("/deleteFavorite")
	public JsonMsg deleteFavorite(HttpSession session,int id) {
		User user = (User) session.getAttribute(ConstantClass.USER_KEY);
		int result = articleService.deleteFavorite(user.getId(),id);
		if (result > 0) {
			return JsonMsg.success();
		}else {
			return JsonMsg.error().addInfo("error", "取消收藏失败");
		}
	}
	
	/**
	 * 完成es搜索功能
	 * @Title: deleteFavorite 
	 * @Description: TODO
	 * @param session
	 * @param id
	 * @return
	 * @return: JsonMsg
	 */
	@RequestMapping("/esSearch")
	public String esSearch(Model model,String key,@RequestParam(defaultValue = "1")int page) {
		//es仓库
		//根据标题搜索
		List<Article> findByTitle = articleResp.findByTitle(key);
		PageInfo<Article> pageInfo = ESHLUtil.selectPageObjects(Article.class, page,3,new String[] {"title"},"id", key).getPageInfo();
		model.addAttribute("info", pageInfo);
		model.addAttribute("key", key);		
		model.addAttribute("page", page);		
		model.addAttribute("total",findByTitle.size());		
		return "index";
	}
	
	
	
}
