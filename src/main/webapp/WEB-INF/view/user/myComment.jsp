<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
</head>
<body>
	<table class="table  table-striped table-hover ">	
		<thead>
			<tr>
				<th>id</th>
				<th>文章标题</th>
				<th>内容</th>
				<th>获赞</th>
				<th>评论日期</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${info.list }" var="comment">
				<tr>
					<td>${comment.id }</td>
					<td>${comment.article.title }</td>
					<td>${comment.content }</td>
					<td>${comment.likeNum }</td>
					<td>${comment.created }</td>
					<td>
						<button type="button" class="btn btn-danger">删除</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<%@include file="../common/page.jsp" %>
	</div>
</body>
</html>