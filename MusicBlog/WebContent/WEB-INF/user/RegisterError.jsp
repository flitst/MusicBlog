<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${title}</title>
	</head>
	<body>
		<font color="red">错误信息为: ${msg}</font>
	    <!-- 保留原页面信息的脚本回退方式 -->
	    <a href="javascript:history.back()">返回注册页面</a>
	    <a href="${pageContext.request.contextPath}/UserControl.do?index">返回站点首页</a>
	</body>
</html>