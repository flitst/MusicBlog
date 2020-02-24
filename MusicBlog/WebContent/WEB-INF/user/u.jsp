<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>修改密码</title>
	</head>
	<body>
		<form action="UserServlet.do?updatePwd" method="post">
			<input type="text" name="pwd" value=""/>
			<input type="submit" value="点击修改" />
		</form>
		<a href="User.jsp">取消修改</a>
	</body>
</html>