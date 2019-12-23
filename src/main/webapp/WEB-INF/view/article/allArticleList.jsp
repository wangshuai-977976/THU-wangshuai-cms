<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <div class="table-responsive">
	<ul class="nav nav-pills">
		<li role="presentation" class="${status == 2 ? 'active' : ''  }"><a href="javascript:showContext('/article/articleList?status=2','ckwz')">全部</a></li>
		<li role="presentation" class="${status == 0 ? 'active' : ''  }"><a href="javascript:showContext('/article/articleList?status=0','ckwz')">待审核<span class="badge badge-success pull-right ">${notCheckCount }</span></a></li>
		<li role="presentation" class="${status == 1 ? 'active' : ''  }"><a href="javascript:showContext('/article/articleList?status=1','ckwz')">审核通过</a></li>
		<li role="presentation" class="${status == -1 ? 'active' : ''  }"><a href="javascript:showContext('/article/articleList?status=-1','ckwz')">审核未通过</a></li>
	</ul>
	<table class="table  table-striped table-hover ">
  	<thead>
	   <th>id</th>
       <th>标题</th>
       <th>频道</th>
       <th>分类</th>
       <th>作者</th>
       <th>发布日期</th>
       <th>状态</th>
       <th>热门状态</th>
       <th>操作</th>
  	</thead>
	<tbody>
		<c:forEach items="${info.list }" var="article">
			<tr>
				<td>${article.id }</td>
				<td>${article.title }</td>
				<td>${article.channel.name }</td>
				<td>${article.category.name }</td>
				<td>${article.user.username }</td>
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
					<span class="${article.hot == 0 ? 'text-danger' : 'text-success' }">${article.hot == 0 ? '未热门' : '热门' }</span>						
				</td>
				<td>
					  <!-- 按钮触发模态框 -->
					<button class="btn btn-success" data-toggle="modal" data-target="#myModal" onclick="getCheckArticle(${article.id})" >审核</button>
					<input type="button" class="btn btn-danger"  onclick = "deleteArticle(${article.id})" value="删除">
				</td>
			</tr>
		</c:forEach>
	</tbody>  	
  </table>
  <%@include file="../common/page.jsp" %>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width:1000px;height:1000px" >
                <div class="modal-content">
                        <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title" id="myModalLabel">文章审核</h4>
                        </div>
                        <div class="modal-body" style="height:500px;overflow-x:scroll;overflow-y:scroll">
                        	<div id="articleTitle"></div>
                        	<div id="articleChannel"></div>
                        	<div id="articleContext"></div>
                        	<div id="articleUser"></div>
                        </div>
                        <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <button type="button" class="btn btn-success" onclick="checkArticleStatus(1)">通过</button>
                                <button type="button" class="btn btn-danger" onclick="checkArticleStatus(-1)">不通过</button>
                                <button type="button" class="btn btn-info" onclick="checkArticleHot(1)">上热门</button>
                                <button type="button" class="btn btn-waring" onclick="checkArticleHot(0)">下热门</button>
                        </div>
                </div><!-- /.modal-content -->
        </div><!-- /.modal -->
</div>

<script type="text/javascript">
	
	//删除文章
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
	//分页
	function goPage(page) {
		var url = "/article/articleList?page="+page+"&status=${status}";
		$("#context").load(url);
	}
	//文章id
	var uid = null; 
	function getCheckArticle(id) {
		$.ajax({
			url:"/article/getCheckArticle",
			type:"post",
			data:{id:id},
			dataType:"json",
			success:function(data){
				if (data.status == 100) {
					$("#articleTitle").text(data.info.article.title);
					$("#articleChannel").text("频道:"+data.info.article.channel.name+"分类:"+data.info.article.category.name);
					$("#articleContext").html(data.info.article.content);
					var oDate= new Date(data.info.article.created);
					    oYear = oDate.getFullYear(),  
					    oMonth = oDate.getMonth()+1,  
					    oDay = oDate.getDate(),  
					    oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay);
					$("#articleUser").text(data.info.article.user.username+"发布于"+oTime);
					uid = data.info.article.id;
				}else{
					alert(data.info.error);
				}			
			}
		});
	}
	//修啊该装填
	function checkArticleStatus(status) {
		$.ajax({
			url:"/article/checkArticleStatus",
			type:"post",
			data:{id:uid,status:status},
			dataType:"json",
			success:function(data){
				if (data.status == 100) {
					alert("修改成功");
					$('#myModal').modal('hide')
				}else{
					alert(data.info.error);
				}			
			}
		});
	}
	//修该热门
	function checkArticleHot(hot) {
		$.ajax({
			url:"/article/checkArticleHot",
			type:"post",
			data:{id:uid,hot:hot},
			dataType:"json",
			success:function(data){
				if (data.status == 100) {
					alert("修改成功");
					$('#myModal').modal('hide')
				}else{
					alert(data.info.error);
				}			
			}
		});
	}
	//框框关闭之后执行的操作
	$('#myModal').on('hidden.bs.modal', function () {
		$("#context").load("article/articleList?page=${info.pageNum}&status=${status}");
	})
	 function getzf(num){  
	     if(parseInt(num) < 10){  
	         num = '0'+num;  
	     }  
	     return num;  
	 }
	
</script>