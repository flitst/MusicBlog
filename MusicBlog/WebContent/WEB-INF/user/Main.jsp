<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.explorer.musicblog.pojo.User,java.io.*,com.explorer.musicblog.util.FileUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	        #center{
	        	positon:absolute;
				margin-left:35%;
				margin-top:15%;
	        }
	        body table tr td img {
	        	width:50px;
	        	height:50px;
	        	border-radius:50%;
	        }
	        textarea{
	        	width:400px;
	        	height:500px;
	        }
	        #article{
	        	width:1000px;
	        	margin:0px auto;
	        	background:#f1f5f5;
	        }
	        #article .preview{
	        	width:100%;
	        	height:300px;
	        	margin:0px auto;
	        }
	        #article .preview .title{
	        }
	        #article .preview .lable{
	        	float:right;
	        }
        </style>
	</head>
	<body>
		${msg}
		<hr>
		<span style="color:red">${error_msg}</span><br>
		<c:choose>
			<c:when test="${sessionScope.user != null}">
				<table>
					<tr>
						<td>头像</td>
						<td>账号</td>
						<td>昵称</td>
						<td>个性签名</td>
						<td>年龄</td>
						<td>性别</td>
						<td>爱好</td>
						<td>图片</td>
						<td>邮箱</td>
						<td>手机</td>
						<td>注册时间</td>
						<td>修改时间</td>
					</tr>
					<tr>
						<td> <img alt="头像" src="${user.getHead()}"/> </td>
						<td> ${user.getAccount()} </td>
						<td> ${user.getNickname()} </td>
						<td> ${user.getSignature()} </td>
						<td> ${user.getAge()} </td>
						<td> 
							<c:choose>
								<c:when test="${user.getSex() == 1}">
									<c:out value="男"></c:out>
								</c:when>
								<c:when test="${user.getSex() == 2}">
									<c:out value="女"></c:out>
								</c:when>
								<c:otherwise>
									<c:out value="保密"></c:out>
								</c:otherwise>
							</c:choose>
						</td>
						<td> 
							<c:forEach items="${user.getHobby()}" var="hobby">
									<c:out value="${hobby}"></c:out>
							</c:forEach></td>
						<td> <img alt="用户图片" src="${user.getImage()}"></td>
						<td> ${user.getEmail()}</td>
						<td> ${user.getMobile()}</td>
						<td> ${user.getCreateTime()}</td>
						<td> ${user.getUpdateTime()}</td>
					</tr>
				</table>
				<br><hr/><br>
				<div id="controlPanel">
					<a href="${pageContext.request.contextPath}/UserControl.do?params=update">修改个人信息</a>&nbsp;
					<a href="${pageContext.request.contextPath}/UserControl.do?params=updatepwd">修改密码</a>&nbsp;
					<a href="${pageContext.request.contextPath}/UserServlet.do?params=logout">退出登录</a>&nbsp;
					<a href="${pageContext.request.contextPath}/UserControl.do?params=upload">上传音乐</a>&nbsp;
					<a href="${pageContext.request.contextPath}/ArticleControlServlet.do?params=pushArticle&id=${user.getId()}">发布文章</a>&nbsp;
					<a href="${pageContext.request.contextPath}/ArticleServlet.do?params=getUserArticle&id=${user.getId()}">获取所有文章</a>&nbsp;
				</div>
				<br>
				<hr/>
				<div id="article">
					<c:if test="${allArticles != null}">
						<c:forEach items="${allArticles}" var="article">
							<div class="preview">
								<h3 class="title">${article.getTitle()}</h3><br>
								引用（参考）：${article.getReference()}<br>
								作者：${article.getUid()}<br><br>
								发布时间：${article.getCreateTime()}&nbsp;修改时间：${article.getUpdateTime()}<br>
								描述：<p>${article.getDescription()}</p><br><br>
								内容：<p>${article.getBody()}</p><br><br>
								<span class="lable">浏览人数:${article.getViews()}&nbsp;&nbsp;评论人数:${article.getCommentCount()}&nbsp;&nbsp;点赞:${article.getLike()}&nbsp;&nbsp;</span>
							</div>
							<hr>
						</c:forEach>
					</c:if>
				</div>
			</c:when>
			<c:otherwise>
				<jsp:forward page="UserControl.do?params=login"></jsp:forward>
			</c:otherwise>
		</c:choose>
	</body>
</html>