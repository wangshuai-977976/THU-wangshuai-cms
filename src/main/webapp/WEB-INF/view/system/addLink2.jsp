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
<title>ZSW公司CMS管理平台</title>
</head>
<body>
	<div class="hrms_container">
		<!-- 导航条 -->
		<div class="hrms_brand_nav">
			<nav class="navbar navbar-inverse navbar-static-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#hrms-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">CMS文章管理系统</a>
				</div>

				<!-- 导航条 -->
				<div class="collapse navbar-collapse" id="hrms-navbar-collapse-1">
					  <ul class="nav navbar-nav">
                          <li class="active">
                            <a href="#">公司介绍<span class="sr-only">(current)</span>  </a>
                          </li>
                          <li><a href="#">文章排行</a></li>
                          <li><a href="#">博客总结</a></li>
                          <li><a href="#">出勤记录</a></li>
                        </ul>

					  <form class="navbar-form navbar-left">
                            <div class="form-group">
                              <input type="text" class="form-control" placeholder="站内搜索">
                            </div>
                            <button type="submit" class="btn btn-default">搜索</button>
                        </form> 
					<!-- 用户设置 -->
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">账号管理 <span class="caret"></span></a>
							<ul class="dropdown-menu nav nav-pills nav-stacked">
								<li class="active"><a href="#"><span
										class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
										用户设置</a></li>
								<li><a href="/user/myCenter"><span class="glyphicon glyphicon-user"
										aria-hidden="true"></span> 个人中心</a></li>
								<li class="hrms_logout"><a href="/user/outLogin"><span
										class="glyphicon glyphicon-off" aria-hidden="true"></span>
										账号退出</a></li>
							</ul></li>
					</ul>
					<!-- /.nav navbar-nav navbar-right -->
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid --> </nav>
		</div>
		<!-- /.hrms_brand_nav -->

		<!-- 中间部分（包括左边栏和员工/部门表单显示部分） -->
		<div class="hrms_body" style="position: relative; top: -15px;">

			<!-- 左侧栏 -->
			<div class="panel-group col-sm-2" id="hrms_sidebar_left" role="tablist" aria-multiselectable="true">
				<ul class="nav nav-pills nav-stacked index_sidebar">
					<li role="presentation" class="active"><a href="#"> 
							<i class="ti-home" aria-hidden="true">首页</i>
							<input type="hidden">
					</a></li>
				</ul>
				<ul class="nav nav-pills nav-stacked article_sidebar">
					<li role="presentation" class="active">
						<a href="#" data-toggle="collapse" data-target="#collapse_article">
							 <span class="ion-android-note " aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;文章管理<span class="pull-right"><i class="ion-chevron-down"></i></span>
						</a>
						<ul class="nav nav-pills nav-stacked " id="collapse_article" >
							<li role="presentation" id="ckwz" ><a href = "javascript:showContext('/article/articleList','ckwz')"><i class="ti-pencil-alt"></i>&nbsp;&nbsp;&nbsp;查看文章</a></li>
							<li role="presentation"><a href="#"><i class="mdi mdi-lightbulb-outline"></i>&nbsp;&nbsp;&nbsp;待审核文章<span class=" badge badge-info pull-right ">3</span></a></li>
							<li role="presentation" ><a href = "javascript:showContext('/link/linkList')"><i class="ti-pencil-alt"></i>&nbsp;&nbsp;&nbsp;查看友情链接</a></li>
							<li role="presentation"><a href="/link/addLink"><i class="mdi mdi-lightbulb-outline"></i>&nbsp;&nbsp;&nbsp;添加友情链接</a></li>
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
							<span class="mdi mdi-account-settings-variant" aria-hidden="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户管理</span><span class="pull-right"><i class="ion-chevron-down"></i></span>
					</a>
						<ul class="nav nav-pills nav-stacked collapse" id="collapse_user">
							<li role="presentation" id="yhgl"><a href="javascript:showContext('/user/users','yhgl')"><i class="mdi mdi-account-multiple"></i>&nbsp;&nbsp;&nbsp;用户列表</a></li>
							<li role="presentation" class=""><a href="#"><i class="mdi mdi-account-plus"></i>&nbsp;&nbsp;&nbsp;添加用户</a></li>
						</ul></li>
				</ul>

			</div>
			<!-- /.panel-group，#hrms_sidebar_left -->

			<!-- 中间员工表格信息展示内容 -->
			<div class="col-sm-10 " id="context" >
				
				<div class="">
					<form:form modelAttribute="link" id="linkForm" type="post" action="/link/addLink">
						链接地址<form:input path="url" /><form:errors path="url"/>
						<br>
						链接名称<form:input path="text" /><form:errors path="text"/>
						<br>
						<form:button>添加</form:button>
			<!-- 			<button type="button" onclick="addLink()" class="btn btn-success">添加链接</button> -->
					</form:form>
				</div>
			</div>
			<!-- /.hrms_body -->


			<nav class="navbar navbar-fixed-bottom" role="navigation">
				<div align="center">
					  <footer class="footer">
	                © 2019 GigData - 1707D <i class="mdi mdi-heart text-danger"></i> ZSW.
	           		 </footer>
				</div>
			</nav>
			

		</div>
	</div>
	<!-- js -->
	<script type="text/javascript">
	
		
		function showContext(url,liId) {
			$("#"+liId).addClass("list-group-item-success");
 			$("#context").load(url);
		}
	</script>
</body>

</html>