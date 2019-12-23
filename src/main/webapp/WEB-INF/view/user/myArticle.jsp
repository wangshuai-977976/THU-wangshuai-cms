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
       <th>状态</th>
       <th>操作</th>
  	</thead>
	<tbody>
		<c:forEach items="${info.list }" var="article">
			<tr>
				<td>${article.id }</td>
				<td>${article.title }</td>
				<td>${article.channel.name }</td>
				<td>${article.category.name }</td>
				<td>${article.created }</td>
				<td>
					<c:choose>
						<c:when test="${article.status == 1 }">
							<span class="text-success">审核通过</span>						
						</c:when>
						<c:when test="${article.status == -1 }">
							<span class="text-danger">审核未通过</span>						
						</c:when>
						<c:when test="${article.status == 0 }">
							<span class="text-info">待审核</span>						
						</c:when>
					</c:choose>
				</td>
				<td>
					<input type="button" class="btn-success" value="修改" onclick="updateArticle(${article.id})">
					<input type="button" class="btn-danger"  onclick = "deleteArticle(${article.id})" value="删除">
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
			url:"/article/deleteArticle",
			type:"post",
			data:{id:id},
			dataType:"json",
			success:function(data){
				if (data.status == 100) {
					alert("删除成功");
				}else{
					alert(data.info.error);
				}			
					$("#context").load("/article/myArticle");
			}
		});
	}
	function goPage(page) {
		var url = "/article/myArticle?page="+page;
		$("#context").load(url);
	}
	
	function updateArticle(id) {
		$("#context").load("/article/updateArticle?id="+id);
	}
	
	
</script>