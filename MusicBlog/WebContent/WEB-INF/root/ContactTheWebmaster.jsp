<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${title}</title>
		<style type="text/css">
			textarea{
				width:500px;
				height:500px;
			}
		</style>
	</head>
	<body>
		<form action="RootServlet.do?params=contact">
			<textarea placeholder="请输入对站长说的话" name="value"></textarea><br>
			<span class="code"></span><br>
			<input type="submit" value="发送">
		</form>
	</body>
</html>