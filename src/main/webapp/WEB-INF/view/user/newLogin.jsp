<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登入</title>
<link href="<%=request.getContextPath() %>/static/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/static/css/jquery/icons.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/bootstrap/js/bootstrap.js"></script>
<link href="<%=request.getContextPath() %>/static/css/style.css" rel="stylesheet">
<script src="<%=request.getContextPath() %>/static/js/index.js"></script>
</head>
<body>

<div class="cotn_principal">
  <div class="cont_centrar">
    <div class="cont_login">
      <div class="cont_info_log_sign_up">
        <div class="col_md_login">
          <div class="cont_ba_opcitiy">
            <h2>LOGIN</h2>
            <p>Lorem ipsum dolor sit amet, consectetur.</p>
            <button class="btn_login" onClick="cambiar_login()">LOGIN</button>
          </div>
        </div>
        <div class="col_md_sign_up">
          <div class="cont_ba_opcitiy">
            <h2>SIGN UP</h2>
            <p>Lorem ipsum dolor sit amet, consectetur.</p>
            <button class="btn_sign_up" onClick="cambiar_sign_up()">SIGN UP</button>
          </div>
        </div>
      </div>
      <div class="cont_back_info">
        <div class="cont_img_back_grey"> <img src="<%=request.getContextPath() %>/static/images/po.jpg" alt="" /> </div>
      </div>
      <div class="cont_forms" >
        <div class="cont_img_back_"> <img src="<%=request.getContextPath() %>/static/images/po.jpg" alt="" /> </div>
        <form action="" id="loginForm">
	        <div class="cont_form_login"> <a href="#" onClick="ocultar_login_sign_up()" ><i class="material-icons">&#xE5C4;</i></a>
	          <h2>LOGIN</h2>
	          <input type="text"name="username" placeholder="username" value="17565"/>
	          <input type="password" name="password" placeholder="Password" value="17565"/>
	          <img alt="" src="/user/getCodeStr" id="codeImage" onclick="changeCode()">
			  <input type="text" name="code" placeholder="Code">
	          <button type="button" class="btn_login" id="login-button">LOGIN</button>
	        </div>
        </form>
        <form action="" id="regForm">
	        <div class="cont_form_sign_up"> <a href="#" onClick="ocultar_login_sign_up()"><i class="material-icons">&#xE5C4;</i></a>
	          <h2>SIGN UP</h2>
	          <input type="text" name="username" id="username"  placeholder="username" />
	          <input type="password" name="password" id="password"  placeholder="password" />
	          <input type="password" name="password2" id="password2"  placeholder="Confirm Password" />
	          <button type="submit" class="btn_sign_up">SIGN UP</button>
	        </div>
        </form>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
$(function () {
	if ("${choose}" == "login") {
		cambiar_login();
	}else if ("${choose}" == "register"){
		cambiar_sign_up();	
	}
})
$('#login-button').click(function(event){
 	$.ajax({
		url:"/user/goLogin",
		type:"post",
		data:$("#loginForm").serialize(),
		dataType:"json",
		success:function(data){
			if (data.status == 100) {
				alert("登入成功");
				if (data.info.user_admin) {
					if ("${gotoArticle}" != null && "${gotoArticle}" != "") {
						location = "article/getArticleById?id=${gotoArticle}";
					}else{
						location = "/admin";
					}
				}else{
					location = "/index";
				}
				
			}else{
				alert(data.info.error);
				location.reload(true);
			}			
		}
	});
});
function changeCode() {
	var src = $("#codeImage").attr("src");
	src = src + "?math="+Math.random();
	$("#codeImage").attr("src",src);
	
}
$("#regForm").validate({
	rules:{
		username:{
			minlength:6
		},password:{
			required:true,
		},password2:{
			required:true,
			minlength:6,
			equalTo:password
		}
	},messages:{
		username:{
			minlength:"用户名最少6位",
			maxlength:"用户名最多8位",
			remote:"该用户已存在"
		},password:{
			required:"密码不可为空",
			minlength:"密码最少6位"
		},password2:{
			required:"密码不可为空",
			minlength:"密码最少6位",
			equalTo:"两次输入不一致"
		}
	},submitHandler:function(){
		  	$.ajax({
			url:"/user/register",
			type:"post",
			data:$("#regForm").serialize(),
			dataType:"json",
			success:function(data){
				if (data.status == 100) {
					alert("注册成功");
					cambiar_login();
				}else{
					alert(data.info.register_error);
				}			
			}
		});
	}
});
</script>
</body>
</html>