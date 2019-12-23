<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <div class="table-responsive">
  <table class="table">
  	<thead>
	   <th>id</th>
       <th>标题</th>
       <th>频道</th>
       <th>分类</th>
       <th>发布日期</th>
       <th>操作</th>
  	</thead>
	<tbody>
		<c:forEach items="${info.list }" var="article">
			<tr>
				<td>${article.id }</td>
				<td><a href="/article/getArticleById?id=${article.id }&protal=favorite">${article.title }</a></td>
				<td>${article.channel.name }</td>
				<td>${article.category.name }</td>
				<td>${article.created }</td>
				<td>
					<input type="button" class="btn-danger"  onclick = "deleteArticle(${article.id})" value="取消收藏">
				</td>
			</tr>
		</c:forEach>
	</tbody>  	
  </table>
  <%@include file="../common/page.jsp" %>
</div>
<script type="text/javascript">
	function deleteArticle(id) {
		$.ajax({
			url:"/article/deleteFavorite",
			type:"post",
			data:{id:id},
			dataType:"json",
			success:function(data){
				if (data.status == 100) {
					alert("删除成功");
				}else{
					alert(data.info.error);
				}		
					$("#context").load("/article/myFavorite");
			}
		});
	}
	function goPage(page) {
		var url = "/article/myFavorite?page="+page;
		$("#context").load(url);
	}
	
	
	
</script>