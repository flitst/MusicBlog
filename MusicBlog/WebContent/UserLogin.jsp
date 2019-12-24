<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>登录</title>
		<style type="text/css">
			#center{
				positon:absolute;
				margin-left:35%;
				margin-top:15%;
			}
		</style>
	</head>
	<body>
		<div id="center">
			<span style="color:red">${msg}</span><br>
			<form action="UserServlet.do?login" method="post">
				<b>用户名：</b>
				<input type="text" id="id" name="account" value="${account}" placeholder="请输入用户名" onblur="checkId()"><span id="id_msg"></span><br>
				<b>密码：</b>
				<input type="password" id="pwd" name="pwd" value="${pwd}" placeholder="请输入密码" onblur="checkPwd()"><span id="pwd_msg"></span><br>
				免登陆三天<input id="rememberMe" type="checkbox" name="rememberMe"/>&nbsp;&nbsp;
				<input type="reset" value="重新输入">&nbsp;&nbsp;
				<input type="submit" value="登录">&nbsp;&nbsp;
				<a id="register" href="UserServlet.do?register">注册</a>&nbsp;&nbsp;
				<a id="register" href="UserServlet.do?forgetPWD">忘记密码</a>&nbsp;&nbsp;
			</form>
		</div>
		<script type="text/javascript">
			function getId(id){
				return document.getElementById(id);
			}
			var id = getId("id");
			var pwd = getId("pwd");
			function checkId(){
				var pwd_msg = getId("id_msg");
				if(id.value != ''){
					pwd_msg.innerText="";
				} else {
					pwd_msg.style.color='red';
					pwd_msg.innerText="用户名不能为空!";
					pwd_msg.onfocus;
				}
			}
			function checkPwd(){
				var pwd_msg = getId("pwd_msg");
				if(pwd.value != ''){
					pwd_msg.innerText="";
				} else {
					pwd_msg.style.color='red';
					pwd_msg.innerText="密码不能为空!";
					pwd_msg.onfocus;
				}
			}
		</script>
	</body>
</html>