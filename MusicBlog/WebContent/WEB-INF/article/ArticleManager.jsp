<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${title}</title>
		<style type="text/css">
			table {
	            border-collapse: collapse;
	            text-align:center;
	            border: none;
	            width: 100%;
	        }
	        td {
	            border:1px solid blue;
	        }
		</style>
	</head>
	<body>
		${msg}
		<table>
			<thead>
				<tr>
					<td>ID</td>
					<td>标题</td>
					<td>引用</td>
					<td>描述</td>
					<td>作者</td>
					<td>内容</td>
					<td>浏览总量</td>
					<td>评论总数</td>
					<td>点赞数量</td>
					<td>创建时间</td>
					<td>修改时间</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${articles != null}">
					<c:forEach items="${articles}" var="article">
						<tr>
							<td>${article.getId()}</td>
							<td>${article.getTitle()}</td>
							<td>${article.getReference()}</td>
							<td>${article.getDescription()}</td>
							<td>${article.getUid()}</td>
							<td>${article.getBody()}</td>
							<td>${article.getViews()}</td>
							<td>${article.getCommentCount()}</td>
							<td>${article.getLike()}</td>
							<td>${article.getCreateTime()}</td>
							<td>${article.getUpdateTime()}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</body>
</html>