<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.explorer.musicblog.pojo.Article"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${title}</title>
		<style type="text/css">
			textarea{
	        	width:100%;
	        	height:500px;
	        }
		</style>
	</head>
	<body>
		<c:if test="${articles != null}">
			<c:forEach items="${articles}" var="article">
					<div>
						<b>${article.getTitle()}</b><br><br>
						引用 :<span>${article.getReference()}</span><br><br>
						描述 :<span>${article.getDescription()}</span><br><br>
						作者 :<span>${article.getUid()}</span><br>
						内容 :<textarea readonly="readonly">${article.getBody()}</textarea><br><br>
						浏览量 :<span>${article.getViews()}</span><br>
						评论总数 :<span>${article.getCommentCount()}</span><br>
						点赞数量 :<span>${article.getLike()}</span><br>
						创建时间 :<span>${article.getCreateTime()}</span>&nbsp;
						修改时间 :<span>${article.getModifyTime()}</span><br><br>
					</div>
			</c:forEach>
		</c:if>
	</body>
</html>