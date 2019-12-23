<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- 导航条 -->
<nav class="navbar navbar-default">
  <div class="container-fluid">
  	<!-- logo -->
  	<div class="navbar-header">
      <a class="navbar-brand" href="/">
      </a>
    </div>
    <!-- 搜索框和右侧登录信息 -->
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#hrms-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/">头条首页</a>
				</div>

				<!-- 导航条 -->
					  <ul class="nav navbar-nav">
                          <li class="active">
                            <a href="#">网站介绍<span class="sr-only">(current)</span>  </a>
                          </li>
                          <li><a href="#">文章排行</a></li>
                          <li><a href="#">博客排行</a></li>
                          <li><a href="#">下载APP</a></li>
                        </ul>

					  <form class="navbar-form navbar-left" action="/article/esSearch" method="post">
                            <div class="form-group">
                              <input type="text"  name="key" class="form-control" placeholder="站内搜索">
                            </div>
                            <button type="submit" class="btn btn-default">搜索</button>
                        </form> 
      <!-- 个人头像信息 -->
      <c:if test="${sessionScope.user == null}">
      	<div class="pull-right">
	      	<a href="/login?choose=login">登入</a>
	      	<a href="/login?choose=register">注册</a>
      	</div>
      </c:if>
      <c:if test="${sessionScope.user != null}">
	      <ul class="nav navbar-nav navbar-right">
	      	<li class="list-inline float-right mb-0">
	          	<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">
	              <img src="<%=request.getContextPath() %>/static/images/IMG_0832.JPG" alt="user" width="40px" height="40px" class="img-circle">
	           </a>
				<ul class="dropdown-menu" >
		            <li><a href="/user/myCenter">
		            	<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>个人中心</a></li>
		            <li><a href="#">
		            	<span class="glyphicon glyphicon-user" aria-hidden="true"></span>修改头像</a></li>
		            <li role="separator" class="divider"></li>
		            <li><a href="/user/outLogin">
		            	<span class="glyphicon glyphicon-off" aria-hidden="true"></span>退出登录</a></li>
				</ul>
	        </li>   						
	      </ul>
      </c:if>
    </div><!-- /.navbar-collapse -->
    
  </div>
</nav>
    
