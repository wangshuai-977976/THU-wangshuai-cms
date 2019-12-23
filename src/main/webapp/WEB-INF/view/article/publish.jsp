<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	request.setCharacterEncoding("UTF-8");
	String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";

%>
<html>
<head>
<meta charset="UTF-8">
<title>文章发布</title>
</head>
	<script>
		KindEditor.ready(function(K) {
			window.editor1 = K.create('textarea[name="content1"]', {
				cssPath : '/static/kindeditor/plugins/code/prettify.css',
				uploadJson : '/static/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '/static/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
		function query(){
		alert(editor1.html())
			//alert( $("[name='content1']").attr("src"))
		} 
	</script>
<body>

		<form action="" id="form">
		<div class="form-group row ">
			<label for="title">文章标题</label> <input type="text"
				class="form-control" id="title" name="title" placeholder="请输入标题">
		</div>


		<div class="form-group row ">
			<textarea name="content1" cols="100" rows="8"
				style="width: 860px; height: 250px; visibility: hidden;"><%=htmlspecialchars(htmlData)%></textarea>
			<br />
		</div>
		<div class="form-group row ">
			<label for="title">文章标题图片</label> <input type="file"
				class="form-control" id="file" name="file">
		</div>
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
		 var formData = new FormData($( "#form" )[0]);
		 formData.set("content",editor1.html());
		$.ajax({
			url:"/article/postArticle",
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

</script>

</body>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>