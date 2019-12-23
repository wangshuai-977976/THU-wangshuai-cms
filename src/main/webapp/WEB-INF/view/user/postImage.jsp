<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>图片发布</title>
</head>
<body>

		<form action="" id="form">
		<div class="form-group row ">
			<label for="title">图片标题</label> <input type="text"
				class="form-control" id="title" name="title" placeholder="请输入标题">
		</div>
		
		<div class="form-group row " id="imageDiv">
			<label for="title">要添加的图片图片</label> 
			<div id="divVal">
				<input type="file"class="form-control" id="file" name="file">
				图片描述<input type="text" name="desc">
			</div>
		</div>
		<button class = "btn btn-success" type="button" onclick="addImageDiv()">添加图片</button>
		
		<div class="form-group row ">
		  	<label for="channel">文章频道</label> 
			<select class="custom-select custom-select-sm mb-3" id="channel"  name="channelId">
			  <option value="0">请选择</option>
			  <c:forEach items="${channelList}" var="channel">
			  		<option value="${channel.id}">${channel.name}</option>
			  </c:forEach>
			</select>
			<label for="category">文章分类</label> 
			<select class="custom-select custom-select-sm mb-3" id="category" name="categoryId">
			  <option value="0">请选择</option>
			</select>
			
			<label for="category">文章标签</label> 
				<input name="tags" size="50"/>
			
		</div>
		
		<div class="form-group row" >
		<button type="button" class="btn btn-success" onclick="publish()">发布</button>
		
		</div>
	</form>
	


<script type="text/javascript">
	$(function() {
		$("#channel").change(function() {
			var chId = $("#channel").val();			
			$("#category option:gt(0)").remove();
			$.ajax({
				url:"/article/getCategoryByChannel",
				type:"post",
				data:{chId:chId},
				dataType:"json",
				success:function(data){
					if (data.status == 100) {
						for ( var i in data.info.categorys) {
							$("#category").append("<option value='"+data.info.categorys[i].id+"'>"+data.info.categorys[i].name+"</option>");
						}
					}else{
						alert(data.info.error);
					}			
				}
			});
			
		});
	})
	function publish() {
		var formData = new FormData($("#form")[0]);
		$.ajax({
			url:"/article/postImage",
			type:"post",
			data:formData,
			dataType:"json",
			// 告诉jQuery不要去处理发送的数据
			processData : false,
			// 告诉jQuery不要去设置Content-Type请求头
			contentType : false,
			success:function(data){
				if (data.status == 100) {
					alert("发布成功");
					location = "/user/myCenter"
				}else{
					alert(data.info.error);
				}			
			}
		});
	}
	
	function addImageDiv() {
		$("#imageDiv").append($("#divVal").html());
	}

</script>

</body>
</html>
