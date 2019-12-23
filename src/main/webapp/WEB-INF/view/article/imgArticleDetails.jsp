<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath() %>/static/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/static/css/jquery/icons.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/bootstrap/js/bootstrap.js"></script>
</head>
<body>
	<div class="container">
	
		<h2>${article.title }</h2>
		<div>
					频道:<a href="#">${article.channel.name }</a> 分类:<a href="#">${article.category.name}</a>
					<br> <a href="#">${article.user.username }</a>发布于
					<fmt:formatDate value="${article.created }" pattern="YYYY年MM月dd日" />
					<button class="fa fa-star-o btn" onclick="favoriteArticle(${article.id})">收藏</button>
		</div>
		
		<div id="myCarousel" class="carousel slide" style="minheight:200px">
						<!-- 轮播（Carousel）指标 -->
						<ol class="carousel-indicators">
							<c:forEach items="${article.images }" var="image" varStatus="index">
								<li data-target="#myCarouse${index.index }" data-slide-to="${index.index }" class="${index.index == 0 ? 'active' : ''} "></li>
							</c:forEach>
						</ol>   
						<!-- 轮播（Carousel）项目 -->
								<div class="carousel-inner">
						<c:forEach items="${article.images }" var="image" varStatus="index">
							<div class="item ${index.index == 0 ? 'active' : '' }" style="widows: 800px;height: 300px">
								<a href="#" class="thumbnail">
									<img style="widows: 800px;height: 300px" src="/pic/${image.url }" alt="Second slide">
								</a>
								<div class="carousel-caption">${image.desc }</div>
							</div>
						</c:forEach>
								</div>
						<!-- 轮播（Carousel）导航 -->
						<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
							<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a>
						<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
							<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
				</div>
		
		<hr>
		
		<!-- 写评论 -->
		<div>
			<form action="">
				<input type="hidden" name="articleId" value="${article.id }">
				<div>
					<img alt="" class="pull-left" height="30px" width="30px" class="img-circle" src="/pic/${comment.user.url }" onerror="this.src='/static/images/default_user_url.png'">
					<textarea rows="5"  cols="155" id="content" name="content" placeholder="相对作者说点什么"></textarea>
				</div>
				<div class="pull-right">
					还能输入<span id="contentNum">250</span>&nbsp;&nbsp;
					<button type="button" onclick="pushComment()" class="btn btn-danger">发表评论</button>
				</div>
			</form>
		</div>
		<hr>
		<!-- 文章评论 -->
		<span class="pull-right">${article.hits }次阅读&nbsp;&nbsp;评论数量:${article.commentCnt }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><br><br>
			<div>
			<!-- 	显示文章的评论 -->
			<c:forEach items="${info.list }" var="comment" varStatus="index">
				<!-- 文章的评论 -->
				<div>
					<c:if test="${index.count <= 3 || sessionScope.user != null }">
						<!-- 发表文章的用户 -->
						<div>
							<img alt="" height="30px" width="30px" class="img-circle" src="/pic/${comment.user.url }" onerror="this.src='/static/images/default_user_url.png'">
							<b>${comment.user.username }</b>
								&nbsp;&nbsp;
								${comment.dateDesc }
								<span class=" pull-right">
									<i class="fa fa-thumbs-o-up" onclick="likeComment(${comment.id})"></i>&nbsp;
								</span>
						</div>
						<!-- 发表的内容 -->
						<div>
							<br>
							${comment.content }
							<!-- 评论的恢复 -->
							<div class="row">
								<div class="col-md-1"></div>
								<div class="col-md-11">
									<c:forEach items="${comment.replys }" var="reply">
										<div>
											<b>${reply.fromUser.username }</b>&gt;<b>${reply.toUser.username }</b>
												&nbsp;&nbsp;
												${reply.descDate }
										</div>
										<div>
											<br>
											${reply.content }
										</div>
										<br>
									</c:forEach>
								</div>
							</div>
							
							<div class="pull-right">
								<a onclick="showRelpyDiv(this)">回复</a>&nbsp;&nbsp;&nbsp;
								<a>举报</a>
							</div>
							
								<!-- 回复评论  -->
								<div style="display: none" id="replyDiv">
									<form action="" id="replyForm">
									<input type="hidden" name="commentId" value="${comment.id }">
									<input type="hidden" name="toUserId" value="${comment.user.id }">
										<div>
											<img alt="" class="pull-left" height="30px" width="30px" class="img-circle" src="/pic/${sessionScope.user.url }" onerror="this.src='/static/images/default_user_url.png'">
											<textarea rows="5"  cols="155" id="content" name="content" placeholder="相对作者说点什么"></textarea>
										</div>
										<div class="pull-right">
											<button type="button" onclick="pushReply(this)" class="btn btn-danger">发表回复</button>
										</div>
									</form>
								</div>
								
						</div>
						<hr>
					</c:if>
					<c:if test="${index.count == 3 && sessionScope.user == null }">
						<div class="text-center">
							<a  onclick="location ='/login?scrollTo=true&gotoArticle=${article.id}'" >登入查看${info.total }条热评</a>
						</div>
					</c:if> 
				</div>
			</c:forEach>
		</div>
		<!-- 评论分页 -->
		<c:if test="${sessionScope.user != null }">
			<div>
				  <%@include file="../common/page.jsp" %>
			</div>
		</c:if>
		<!-- 文章的上一篇下一篇 -->	
		<div>
			<nav aria-label="...">
				<ul class="pager">
					<li><a href="/article/getArticleById?id=${article.id }&protal=${protal}&page=pre">上一篇</a></li>
					<li><a href="/article/getArticleById?id=${article.id }&protal=${protal}&page=next">下一篇</a></li>
				</ul>
			</nav>
		</div>
	</div>

	<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog"  >
                <div class="modal-content">
                        <div class="modal-body" style="height:600px;overflow-x:scroll;overflow-y:scroll">
                        
                        </div>
                </div><!-- /.modal-content -->
        </div><!-- /.modal -->
</div>	

	
	<script type="text/javascript">
		$(function() {
			$("#content").keyup(function () {
				var content = $("#content").val();	
				if (content.length >= 250) {
					$("#content").val(content.substring(0,250));
				}
				content = $("#content").val();	
				$("#contentNum").text(250 - content.length);
			});
			 
		})
		function pushComment() {
			$.ajax({
				url:"/comment/pushComment",
				type:"post",
				data:$("form").serialize(),
				dataType:"json",
				success:function(data){
					if (data.status == 100) {
						alert("发布成功");
						location.reload(true);
					}else{
						alert(data.info.error);
					}			
				},error:function(XMLHttpRequest, textStatus){
					var redirect=XMLHttpRequest.getResponseHeader("REDIRECT");
		            if(redirect=="REDIRECT"){
		                alert("请先登录！");
		               window.open(XMLHttpRequest.getResponseHeader("CONTEXTPATH"));
		            }
				}
			});
		}
		
		function likeComment(id) {
			$.ajax({
				url:"/comment/likeComment",
				type:"post",
				data:{id:id},
				dataType:"json",
				success:function(data){
					if (data.status == 100) {
						location.reload(true);
					}else{
						alert(data.info.error);
					}			
				},error:function(XMLHttpRequest, textStatus){
					var redirect=XMLHttpRequest.getResponseHeader("REDIRECT");
		            if(redirect=="REDIRECT"){
		                alert("请先登录！");
		               window.open(XMLHttpRequest.getResponseHeader("CONTEXTPATH"));
		            }
				}
			});
		}
		
		function favoriteArticle(id) {
			$.ajax({
				url:"/article/favoriteArticle",
				type:"post",
				data:{id:id},
				dataType:"json",
				success:function(data){
					if (data.status == 100) {
						alert("收藏成功");
						location.reload(true);
					}else{
						alert(data.info.error);
					}			
				},error:function(XMLHttpRequest, textStatus){
					var redirect=XMLHttpRequest.getResponseHeader("REDIRECT");
		            if(redirect=="REDIRECT"){
		                alert("请先登录！");
		               window.open(XMLHttpRequest.getResponseHeader("CONTEXTPATH"));
		            }
				}
			});
		}
		
		//打开评论的恢复
		function showRelpyDiv(thiz) {
			$(thiz).parent().next().show();
		}
		
		function pushReply(thiz) {
			var formData = $(thiz).parent().parent().serialize();
			$.ajax({
				url:"/comment/pushReply",
				type:"post",
				data:formData,
				dataType:"json",
				success:function(data){
					if (data.status == 100) {
						alert("回复成功");
						location.reload(true);
					}else{
						alert(data.info.error);
					}			
				},error:function(XMLHttpRequest, textStatus){
					var redirect=XMLHttpRequest.getResponseHeader("REDIRECT");
		            if(redirect=="REDIRECT"){
		                alert("请先登录！");
		               window.open(XMLHttpRequest.getResponseHeader("CONTEXTPATH"));
		            }
				}
			});
		}
	</script>
	
	
</body>
</html>