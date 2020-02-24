<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<c:choose>
			<c:when test="${msg != null && msg > 0}">发送成功！站长会尽快处理您反应的情况，谢谢使用本站！欢迎随时向站长发送建议。</c:when>
			<c:otherwise>发送失败！返回<a href="javascript:history.back()">重新发送</a></c:otherwise>
		</c:choose>
	</body>
</html>