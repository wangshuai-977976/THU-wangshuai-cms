<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<%=request.getContextPath() %>/static/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/static/css/jquery/icons.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/bootstrap/js/bootstrap.js"></script>
<title>Insert title here</title>
</head>
<body>
<!-- 导航条 -->

<body>
	<div class="hrms_container">
		<!-- 导航条 -->
		<%@include file="../common/top.jsp" %>
		<!-- 中间部分（包括左边栏和员工/部门表单显示部分） -->
		<div class="hrms_body" style="position: relative; top: -15px;">

			<!-- 左侧栏 -->
			<div class="panel-group col-sm-2" id="hrms_sidebar_left" role="tablist" aria-multiselectable="true">
				<ul class="nav nav-pills nav-stacked index_sidebar">
					<li role="presentation" class="active"><a href="#"> 
							<i class="ti-home" aria-hidden="true">首页</i>
					</a></li>
				</ul>
				<ul class="nav nav-pills nav-stacked article_sidebar">
					<li role="presentation" class="active">
						<a href="#" data-toggle="collapse" data-target="#collapse_article">
							 <span class="ion-android-note" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;文章管理<span class="pull-right"><i class="ion-chevron-down"></i></span>
						</a>
						<ul class="nav nav-pills nav-stacked " id="collapse_article" >
							<li role="presentation"><a href="javascript:showContext('/article/postArticle')"><i class="ti-pencil-alt"></i>&nbsp;&nbsp;&nbsp;编写文章</a></li>
							<li role="presentation"><a href="javascript:showContext('/article/postImage')"><i class="dripicons-photo-group"></i>&nbsp;&nbsp;&nbsp;发布图片</a></li>
							<li role="presentation"><a href="javascript:showContext('/article/myArticle')"><i class="dripicons-to-do"></i>&nbsp;&nbsp;&nbsp;我的文章</a></li>
							<li role="presentation"><a href="javascript:showContext('/link/myFavorite')"><i class="fa fa-star"></i>&nbsp;&nbsp;&nbsp;我的收藏</a></li>
							<li role="presentation"><a href="javascript:showContext('/article/myLikeNum')"><i class="fa fa-thumbs-up"></i>&nbsp;&nbsp;&nbsp;我的点赞</a></li>
							<li role="presentation"><a href="javascript:showContext('/comment/myComment')"><i class="fa fa-pied-piper"></i>&nbsp;&nbsp;&nbsp;我的评论</a></li>
							<li role="presentation"><a href="/link/addUserLink"><i class="fa fa-pied-piper"></i>&nbsp;&nbsp;&nbsp;添加收藏链接</a></li>
						</ul>
					</li>
				</ul>
				<ul class="nav nav-pills nav-stacked vote_sidebar">
					<li role="presentation" class="active">
						<a href="#" data-toggle="collapse" data-target="#collapse_vote"> 
							<span class="ion-stats-bars" aria-hidden="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;投票管理</span><span class="pull-right"><i class="ion-chevron-down"></i></span>
						</a>
						<ul class="nav nav-pills nav-stacked collapse" id="collapse_vote">
							<li role="presentation"><a href="#"><i class="mdi mdi-chart-bar"></i>&nbsp;&nbsp;&nbsp;投票列表</a></li>
							<li role="presentation"><a href="#"><i class="mdi mdi-chart-histogram"></i>&nbsp;&nbsp;&nbsp;新建投票</a></li>
						</ul>
					</li>
				</ul>
				<ul class="nav nav-pills nav-stacked user_sidebar">
					<li role="presentation" class="active">
						<a href="#" data-toggle="collapse" data-target="#collapse_user"> 
							<span class="mdi mdi-account-settings-variant" aria-hidden="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;个人设置</span><span class="pull-right"><i class="ion-chevron-down"></i></span>
					</a>
						<ul class="nav nav-pills nav-stacked collapse" id="collapse_user">
							<li role="presentation"><a href="javascript:shouContext('')"><i class="mdi mdi-account-multiple"></i>&nbsp;&nbsp;&nbsp;编辑信息</a></li>
							<li role="presentation"><a href="#"><i class="mdi mdi-account-plus"></i>&nbsp;&nbsp;&nbsp;我的头像</a></li>
						</ul></li>
				</ul>

			</div>
			<!-- /.panel-group，#hrms_sidebar_left -->

			<!-- 中间员工表格信息展示内容 -->
			<div class="col-sm-10 " id="context" >
				<div class="">
						<form:form modelAttribute="link" id="linkForm" type="post" action="/link/addUserLink">
							链接地址<form:input path="url" /><form:errors path="url"/>
							<br>
							链接名称<form:input path="text" /><form:errors path="text"/>
							<br>
							<form:button>添加</form:button>
				<!-- 			<button type="button" onclick="addLink()" class="btn btn-success">添加链接</button> -->
						</form:form>
					</div>
				<div id="kindEditor" style="display: none">
				   <!-- 引入kindEditor的样式 -->
				  <jsp:include page="/static/kindeditor/jsp/demo.jsp"></jsp:include>
              </div>
			</div>
			<!-- /.hrms_body -->


			<nav class="navbar navbar-inverse navbar-fixed-bottom"
				role="navigation">
			<div align="center">
				<font color="blue" size="5"> ----八维大数据学院1707D--- </font>
			</div>
			</nav>

		</div>
	</div>
	<script type="text/javascript">
		function showContext(url) {
			$("#context").load(url);
		}
	</script>
</body>
</html>