<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.ArrayList" import="java.util.Iterator" import="java.util.List" import="java.util.Map" import="java.util.Map.Entry"%>
<%@ page import="com.explorer.musicblog.pojo.User" %>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户管理</title>
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
		<script type="text/javascript">
			function isDelete(obj,id){
				var bool = confirm("确认删除吗？");
				if(bool){
					obj.href=document.a.href="UserServlet.do?param=delete&id="+id;
				}
				
			}
			function isEnable(obj,id){
				var bool = confirm("确认启用吗？");
				if(bool){
					obj.href="UserServlet.do?param=enableUser&id="+id;
				}
				
			}
			function isDisable(obj,id){
				var bool = confirm("确认禁用吗？");
				if(bool){
					obj.href="UserServlet.do?param=disableUser&id="+id;
				}
				
			}
		</script>
	</head>
	<body>
		<c:choose>
			<c:when test="${requestScope.users != null}">
				<table>
					<tr>
						<td>头像</td>
						<td>账号</td>
						<td>密码</td>
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
						<td>状态</td>
						<td>操作</td>
					</tr>
					<c:forEach items="${requestScope.users}" var="user">
						<tr>
							<td> <img alt="头像" src="${user.getHead()}"/> </td>
							<td> ${user.getAccount()} </td>
							<td> ${user.getPwd()} </td>
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
							<td> ${user.getStatus()}</td>
							<td>
										<c:if test="${user.getStatus() == 0}">
											<a href="javascript:void(0)" onclick="isEnable(this,${user.getId()})" >启用</a> | 
										</c:if>
										<c:if test="${user.getStatus() == 1}">
											<a href="javascript:void(0)" onclick="isDisable(this,${user.getId()})" >禁用</a> | 
										</c:if>
								<a href="javascript:void(0)" onclick="isDelete(this,${user.getId()})" >删除</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			<br><hr/><br>
		</c:when>
			<c:otherwise>
				<jsp:forward page="UserControl.do?param=login"></jsp:forward>
			</c:otherwise>
		</c:choose>

	</body>
</html>