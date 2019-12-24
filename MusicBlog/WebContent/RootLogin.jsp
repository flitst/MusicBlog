<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>后台管理</title>
		<style type="text/css">
			#list{
				positon:absolute;
				margin-left:45%;
				margin-top:20%;
			}
		</style>
	</head>
	<body>
		<div id="list">
			<h3>后台管理</h3>
			<form action="RootLoginServlet.do" method="POST">
				<input type="text" name="name" id="name"/><br>
				<input type="password" name="pwd" id="password"/><br>
				<input type="submit" value="登录"/>
			</form>
		</div>
	</body>
</html>