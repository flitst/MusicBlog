<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<span style="color:red">${error_msg}</span><br>
			<form action="${pageContext.request.contextPath}/UserServlet.do?params=login" method="post" onsubmit="return checkValue();">
				<b>用户名:</b>
				<input type="text" id="id" name="account" value="${account}" placeholder="请输入用户名" onblur="checkId()">
				<span id="id_msg"></span><br><br>
				
				<b>密&nbsp;&nbsp;&nbsp;&nbsp;码:</b>
				<input type="password" id="pwd" name="pwd" value="${pwd}" placeholder="请输入密码" onblur="checkPwd()">
				<span id="pwd_msg"></span><br><br>
				
				免登陆三天<input id="rememberMe" type="checkbox" name="rememberMe"/>&nbsp;&nbsp;
				<input type="reset" value="重新输入">
				<br>
				<p>
					<img alt="验证码" src="${pageContext.request.contextPath}/CodeServlet" id="img">
					<a href="javascript:reloadCode()">看不清楚?</a>
				</p>
				<input type="text" placeholder="请输入验证码" name="piccode" id="code" onblur="checkCode()"><span id="code_msg"></span>
				<br>
				<input type="submit" value="登录">
				<br><br>
				<a id="forget" href="${pageContext.request.contextPath}/UserControl.do?params=forgetpwd">忘记密码?</a>&nbsp;&nbsp;
				<a id="register" href="${pageContext.request.contextPath}/UserControl.do?params=register">注册</a>&nbsp;&nbsp;
			</form>
		</div>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/index.js"></script>
		<script type="text/javascript">

			function checkValue(){
				if(checkId() & checkPwd() & checkCode()){
					return true;
				}
				return false;
			}
			function checkCode(){
				var val = getId("code").value.trim();
				var code_msg = getId("code_msg");
				code_msg.style.color='red';
				if(val != '' & val.length == 4){
					code_msg.innerText="";
					return true;
				} else if (val.length != 4 & val.length > 0){
					code_msg.innerText=" 验证码只能为四位数!";
				} else {
					code_msg.innerText=" 验证码不能为空!";
				}
				code_msg.onfocus;
				return false;
				
			}
			var id = getId("id");
			var pwd = getId("pwd");
			function checkId(){
				var pwd_msg = getId("id_msg");
				if(id.value != ''){
					pwd_msg.innerText="";
					return true;
				} else {
					pwd_msg.style.color='red';
					pwd_msg.innerText=" 用户名不能为空!";
					pwd_msg.onfocus;
					return false;
				}
			}
			function checkPwd(){
				var pwd_msg = getId("pwd_msg");
				if(pwd.value != ''){
					pwd_msg.innerText="";
					return true;
				} else {
					pwd_msg.style.color='red';
					pwd_msg.innerText=" 密码不能为空!";
					pwd_msg.onfocus;
					return false;
				}
			}
			function reloadCode(){
				var img = getId("img");
				var time = new Date().getTime();
				img.src="${pageContext.request.contextPath}/CodeServlet?time="+time;
			}
		</script>
	</body>
</html>