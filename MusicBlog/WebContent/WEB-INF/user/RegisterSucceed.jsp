<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${title}</title>
	</head>
	<body>
		消息${msg}
		<a href="${pageContext.request.contextPath}/UserControl.do?login">登录去</a>
	</body>
</html>