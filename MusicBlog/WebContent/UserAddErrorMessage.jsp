<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加用户消息</title>
	</head>
	<body>
		<h3>添加用户失败!!!</h3>
		错误信息为: ${msg}
		<h2>登陆失败，请重新登陆！</h2>
	    <!-- 保留原页面信息的脚本回退方式 -->
	    <a href="javascript:history.back()">返回</a>
	    <!-- 保留原页面信息的脚本回退方式 -->
	    <!-- <a href="index.jsp">返回</a> -->
		<a href="addUser.jsp">重新注册</a>
	</body>
</html>