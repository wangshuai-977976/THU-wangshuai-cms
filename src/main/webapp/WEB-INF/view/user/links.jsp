<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<title>友情链接列表</title>
</head>
<body>
<table class="table  table-striped table-hover ">	
		<thead>
			<tr>
				<th>id</th>
				<th>url</th>
				<th>名称</th>
				<th>创建日期</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${info.list }" var="link">
				<tr>
					<td>${link.id }</td>
					<td>${link.url}</td>
					<td><a onclick="window.open('${link.url }')">${link.text }</a></td>
					<td><fmt:formatDate value="${link.created }" pattern="YYYY年MM月dd日"/></td>
					<td>
						<button type="button" class="btn btn-danger" onclick="deleteLink(${link.id })">删除</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<%@include file="../common/page.jsp" %>
	</div>
	<script type="text/javascript">11
		function goPage(page) {
			var url = "/link/myFavorite?page="+page;
			$("#context").load(url);
		}
		function deleteLink(id) {
			$.ajax({
				url:"/link/deleteLink",
				type:"post",
				data:{id:id},
				dataType:"json",
				success:function(data){
					if (data.status == 100) {
						alert("删除成功");
					}else{
						alert(data.info.error);
					}			
						$("#context").load("/link/myFavorite");
				}
			});	
		}
	</script>
</body>
</html>