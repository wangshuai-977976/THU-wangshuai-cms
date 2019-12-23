<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<title>sss</title>
</head>
<body>
	<div class="container">
		<ul class="nav nav-tabs">
			  <li class="${caId == 0 ? 'active' : '' }" role="presentation">
			  	<a href="javascript:showChannel('/article/getArticleByCG?chId=${chId }')" >全部</a>
			  	</li>
			<c:forEach items="${cateList }" var="cate">
			  <li class="${cate.id == caId ? 'active' : '' }" role="presentation">
			  	<a href="javascript:showChannel('/article/getArticleByCG?chId=${chId }&caId=${cate.id }')" >${cate.name }</a>
			  </li>
			</c:forEach>
		</ul>
	</div>
		
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
			    Showing ${info.startRow } to ${info.endRow } of ${info.total } entries  
			<ul class="pagination">
			 	
			    <li class=""><a href="javascript:goPage(${info.pageNum == 1 ? 1 : info.pageNum - 1 })" >&laquo;</a></li>
			    <c:if test="${info.pages <= 5 }">
					<c:forEach var="count" varStatus="index" begin="1" end="${info.pages }">
					    <li class="${count == info.pageNum ?  'active' : '' }"><a href="javascript:goPage(${count })" class="">${count }</a></li>
					 </c:forEach>
			    </c:if>
			    
			    <c:if test="${info.pages > 5 }">
				 	<c:choose>
				 		<c:when test="${info.pageNum > 2 && info.pageNum < (info.pages -1) }">
						    <c:forEach var="count" varStatus="index" begin="${info.pageNum -2}" end="${info.pageNum + 2}">
						    	<li class="${count == info.pageNum ?  'active' : '' }"><a href="javascript:goPage(${count })" class="">${count }</a></li>
						    </c:forEach>
				 		</c:when>
				 		<c:when test="${info.pageNum == 2 }">
						    <c:forEach var="count" varStatus="index" begin="${info.pageNum - 1}" end="${info.pageNum + 3}">
						    	<li class="${count == info.pageNum ?  'active' : '' }"><a href="javascript:goPage(${count })" class="">${count }</a></li>
						    </c:forEach>
				 		</c:when>
				 		<c:when test="${info.pageNum == 1 }">
						    <c:forEach var="count" varStatus="index" begin="${info.pageNum}" end="${info.pageNum + 4}">
						    	<li class="${count == info.pageNum ?  'active' : '' }"><a href="javascript:goPage(${count })" class="">${count }</a></li>
						    </c:forEach>
				 		</c:when>
				 		<c:when test="${info.pageNum == info.pages }">
						    <c:forEach var="count" varStatus="index" begin="${info.pageNum - 4}" end="${info.pages}">
						    	<li class="${count == info.pageNum ?  'active' : '' }"><a href="javascript:goPage(${count })" class="">${count }</a></li>
						    </c:forEach>
				 		</c:when>
				 		<c:when test="${info.pageNum == (info.pages - 1)}">
						    <c:forEach var="count" varStatus="index" begin="${info.pageNum - 3}" end="${info.pageNum +1 }">
						    	<li class="${count == info.pageNum ?  'active' : '' }"><a href="javascript:goPage(${count })" class="">${count }</a></li>
						    </c:forEach>
				 		</c:when>
				 	</c:choose>
				 </c:if>
			    
			    <%-- <c:forEach var="count" varStatus="index" begin="${info.pageNum > 2 ? info.pageNum -2 : 1}" end="${info.pageNum + 2 > info.pages ? info.pages : info.pageNum+2}">
			    	<li><a href="javascript:goPage(${count })" class="">${count }</a></li>
			    </c:forEach> --%>
			    
			    <li><a href="javascript:showChannel('/article/getArticleByCG?chId=${chId }&caId=${cate.id }&page=${info.pageNum == info.pages ? info.pageNum : info.pageNum + 1 }')">&raquo;</a></li>
			</ul>			
						</div>
					</div>		
		
		
		
	</div>
	<script type="text/javascript">


</script>
</body>
</html>