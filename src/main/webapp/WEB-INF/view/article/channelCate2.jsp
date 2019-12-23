<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<%=request.getContextPath() %>/static/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/static/css/jquery/icons.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/bootstrap/js/bootstrap.js"></script>
 <title>头条首页</title>
</head>
<body>
<!-- 导航条 -->
<%@include file="../common/top.jsp" %>
<div class="container-fluid" >
	<div class="container" style="minheight:200px" >
		<div class="row">
			<!-- 左侧菜单 -->
			<div class="col-md-2" style="minheight:200px;margin-top:20px" >
					<div class="list-group">
						 <a href="/index" class="list-group-item ${chId == 0 ? 'active' : '' }">热门文章</a>
						<c:forEach items="${channelList }" var="channel">
							<a href="/article/getArticleByCG?chId=${channel.id }" class="list-group-item ${chId == channel.id ? 'active' : '' }">${channel.name }</a>
<%-- 						    <li class="list-group-item" data="article/getArticleByChannelId?channelId=${channel.id }"></li> --%>
						</c:forEach>
					</div>	
			</div>
			<!-- 中间的内容 -->
			<div class="col-md-8" style="background:white;minheight:200px" id="context" >
				<div>
						<hr>
						
				</div>
				
					<div class="container">
		<ul class="nav nav-tabs">
			  <li class="${caId == 0 ? 'active' : '' }" role="presentation">
			  	<a href="/article/getArticleByCG?chId=${chId }" >全部</a>
			  	</li>
			<c:forEach items="${cateList }" var="cate">
			  <li class="${cate.id == caId ? 'active' : '' }" role="presentation">
			  	<a href="/article/getArticleByCG?chId=${chId }&caId=${cate.id }" >${cate.name }</a>
			  </li>
			</c:forEach>
		</ul>
	</div>
				
				
					<!-- 放热门文章的列表 -->
					<div class="container" >
						<c:forEach items="${info.list }" var="hotArticle">
							<div class=row>
								 <hr>
								<div class="col-md-2">
									<img height="50px" width="50px" src="d:/pic/${hotArticle.picture }">
								</div>
								<div class="col-md-10 pull-left">
									<a href="javascript:showArticle('/article/getArticleById?id=${hotArticle.id }&protal=${hotArticle.channel.id},${hotArticle.category.id}')">${hotArticle.title }</a>
									<br>
									频道:<a href="#">${hotArticle.channel.name }</a>
									分类:<a href="#">${hotArticle.category.name}</a>
									<br>
								 	<a href="#">${hotArticle.user.username }</a> 发布于<fmt:formatDate value="${hotArticle.created }" pattern="YYYY年MM月dd日"/>
								</div>
							</div>
						</c:forEach>
						<div class="row">
							<ul class="pagination">
							 	
							    <li class=""><a href="/system/index?page=${info.pageNum == 1 ? 1 : info.pageNum - 1 }" >&laquo;</a></li>
							    
							    <c:if test="${info.pages <= 5 }">
									<c:forEach var="count" varStatus="index" begin="1" end="${info.pages }">
									    <li class="${count == info.pageNum ?  'active' : '' }"><a href="/system/index?page=${count }" class="">${count }</a></li>
									 </c:forEach>
							    </c:if>
							    
							    <c:if test="${info.pages > 5 }">
								 	<c:choose>
								 		<c:when test="${info.pageNum > 2 && info.pageNum < (info.pages -1) }">
										    <c:forEach var="count" varStatus="index" begin="${info.pageNum -2}" end="${info.pageNum + 2}">
										    	<li class="${count == info.pageNum ?  'active' : '' }"><a href="/system/index?page=${count }" class="">${count }</a></li>
										    </c:forEach>
								 		</c:when>
								 		<c:when test="${info.pageNum == 2 }">
										    <c:forEach var="count" varStatus="index" begin="${info.pageNum - 1}" end="${info.pageNum + 3}">
										    	<li class="${count == info.pageNum ?  'active' : '' }"><a href="/system/index?page=${count }" class="">${count }</a></li>
										    </c:forEach>
								 		</c:when>
								 		<c:when test="${info.pageNum == 1 }">
										    <c:forEach var="count" varStatus="index" begin="${info.pageNum}" end="${info.pageNum + 4}">
										    	<li class="${count == info.pageNum ?  'active' : '' }"><a href="/system/index?page=${count }" class="">${count }</a></li>
										    </c:forEach>
								 		</c:when>
								 		<c:when test="${info.pageNum == info.pages }">
										    <c:forEach var="count" varStatus="index" begin="${info.pageNum - 4}" end="${info.pages}">
										    	<li class="${count == info.pageNum ?  'active' : '' }"><a href="/system/index?page=${count }" class="">${count }</a></li>
										    </c:forEach>
								 		</c:when>
								 		<c:when test="${info.pageNum == (info.pages - 1)}">
										    <c:forEach var="count" varStatus="index" begin="${info.pageNum - 3}" end="${info.pageNum +1 }">
										    	<li class="${count == info.pageNum ?  'active' : '' }"><a href="/system/index?page=${count }" class="">${count }</a></li>
										    </c:forEach>
								 		</c:when>
								 	</c:choose>
								 </c:if>
							    
							    <%-- <c:forEach var="count" varStatus="index" begin="${info.pageNum > 2 ? info.pageNum -2 : 1}" end="${info.pageNum + 2 > info.pages ? info.pages : info.pageNum+2}">
							    	<li><a href="javascript:goPage(${count })" class="">${count }</a></li>
							    </c:forEach> --%>
							    
							    <li><a href="/system/index?page=${info.pageNum == info.pages ? info.pageNum : info.pageNum + 1 }">&raquo;</a></li>
							</ul>				
						</div>
					</div>
			</div>
			<!-- 中间的内容结束 -->
			
			
			<div class="col-md-2" style="minheight:200px" >
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 class="panel-title">面板标题</h3>
					</div>
					<div class="panel-body">
						这是一个基本的面板
					</div>
					<div class="panel-body">
						这是一个基本的面板
					</div>
				</div>
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">最新文章</h3>
					</div>
						<div class="panel-body">
							<ul class="pull-left">
								<c:forEach items="${newArticleList }" var="newArticle">
									<li><a href="javascript:showArticle('/article/getArticleById?id=${newArticle.id }&protal=new')">${newArticle.title }</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			
		</div>
		
	</div>

</div>

<script type="text/javascript">
	function showChannel(url) {
		$("#context").load(url);
	}
	function showArticle(url) {
		window.open(url);
	}
</script>

<!-- 底部 -->
<nav class="navbar navbar-default">
  <div class="container-fluid">
  </div>
</nav>

</body>
</html>