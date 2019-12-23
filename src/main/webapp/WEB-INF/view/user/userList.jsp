<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<title>用户管理</title>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">输入姓名</a>
			</div>
			<div>
				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" class="form-control" name="searchName" value="${name }"  placeholder="Search">
					</div>
					<input type="button" class="btn btn-default" onclick="search()" value="查询" />
				</form>
			</div>
		</div>
	</nav>

<div class="table-responsive">
	<div style="height: 330px">
	  <table class="table" >
	    <caption>用户列表</caption>
	    <thead>
	      <tr >
	        <th>用户id</th>
	        <th>用户名称</th>
	        <th>注册日期</th>
	        <th>修改日期</th>
	        <th>角色</th>
	        <th>状态</th>
	        <th>操作</th>
	       </tr>
	    </thead>
	    <tbody>
	    	<c:forEach items="${info.list}" var="user">
		      <tr >
		        <td>${user.id }</td>
		        <td>${user.username }</td>
		        <td><fmt:formatDate value="${user.createTime }" pattern="YYYY年MM月dd日"/></td>
		        <td><fmt:formatDate value="${user.updateTime }" pattern="YYYY年MM月dd日"/></td>
		        <td>
		        	<c:choose>
		        		<c:when test="${user.role == 0 }">普通用户</c:when>
		        		<c:when test="${user.role == 1 }">管理员</c:when>
			        	<c:otherwise>未知</c:otherwise>
		        	</c:choose>
		        </td>
		        <td>${user.locked == 0 ? '正常': '禁用' }</td>
		        <td>
		        		<button type="button" class="btn btn-danger" onclick="updateUserLocked(${user.id },1)">禁用</button>
		        		<button type="button" class="btn btn-info" onclick="updateUserLocked(${user.id },0)">解禁</button>
		        </td>
		       </tr>
	    	</c:forEach>
	    </tbody>
	  </table>
	 </div>
	   <%@include file="../common/page.jsp" %>
</div>
	<script type="text/javascript">
		function updateUserLocked(id,locked) {
			$.ajax({
				url:"/user/updateUserLocked",
				type:"post",
				data:{id:id,locked:locked},
				dataType:"json",
				success:function(data){
					if (data.status == 100) {
						alert("修改成功");
						$("#context").load("/user/users");
					}else{
						alert(data.info.update_status_error);					
						$("#context").load("/user/users");
					}
				}
			});
		}
		function goPage(page) {
			var name = $("[name='searchName']").val();
			var url = "/user/users?page="+page+"&name="+name;
			$("#context").load(url);
		}
		function search() {
			var name = $("[name='searchName']").val();
			var url = "/user/users?name="+name;
			$("#context").load(url);
		}
		
		
	</script>
</body>
</html>