<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="/CatEye/layui/css/layui.css" media="all">
  
		<style>
			body {
				background-color: #e7e7e7;
			}
			input:-webkit-autofill {
				-webkit-box-shadow: inset 0 0 0 1000px #fff;
				background-color: transparent;
			}
			.admin-login-background {
				width: 300px;
				height: 300px;
				position: absolute;
				left: 50%;
				top: 50%;
				margin-left: -150px;
				margin-top: -100px;
			}
			.admin-header {
				margin-top: -100px;
				margin-bottom: 20px;
			}
			
			.admin-logo {
				width: 280px;
			}
			
			.admin-button {
				margin-top: 20px;
			}
			
			.admin-input {
				border-top-style: none;
				border-right-style: solid;
				border-bottom-style: solid;
				border-left-style: solid;
				height: 50px;
				width: 300px;
				padding-bottom: 0px;
			}
			
			.admin-input-username {
				border-top-style: solid;
				border-radius: 10px 10px 0 0;
			}
			
			.admin-input-verify {
				border-radius: 0 0 10px 10px;
			}
			
			.admin-button {
				width: 300px;
				height: 50px;
				border-radius: 4px;
				background-color: #2d8cf0;
			}
			
			.admin-icon {
				margin-left: 260px;
				margin-top: 10px;
				font-size: 30px;
			}
			
			i {
				position: absolute;
			}
			.admin-captcha {
				position: absolute;
				margin-left: 205px;
				margin-top: -40px;
			}
		</style>
	</head>

	<body>
		<div id="container">
			<div class="admin-login-background">
				<form class="layui-form" action="loginInfo" id="login-from" method="post" >
					<div>
						<i class="layui-icon layui-icon-username admin-icon admin-icon-username"></i>
						<input type="text" name="username" placeholder="请输入用户名" autocomplete="off" class="layui-input admin-input admin-input-username">
					</div>
					<div>
						<i class="layui-icon layui-icon-password admin-icon admin-icon-password"></i>
						<input type="password" name="password" placeholder="请输入密码" autocomplete="off" class="layui-input admin-input">
					</div>
					<button class="layui-btn admin-button" type="submit" lay-submit lay-filter="login">登陆</button>
					
				</form>
			</div>
		</div>
		
		<script src="/CatEye/js/jquery-1.11.1.min.js"></script>	
		<script src="/CatEye/layui/layui.js"></script>
		<script>
			layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], function(){
				
				var table = layui.table,
				  element=layui.element,
				  layer=layui.layer,
				  slider=layui.slider,
				  form=layui.form;
				
				/* form.on('submit(login)', function(){
				  	var data=$("#login-from").serialize();
				  	
				     $.ajax({
						url:"loginInfo",
						type:'post',
						data:data,
						success:function(data){
							
						}
					});  
				    return false;
			  }); */
				
			});	
		</script>
	</body>
</html>