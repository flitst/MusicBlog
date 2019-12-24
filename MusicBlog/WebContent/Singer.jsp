<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.explorer.musicblog.pojo.Singer"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>歌手</title>
	</head>
	<body>
		<a href="SingerGetAll.jsp">查询所有歌手</a><br>
		<input type="text" name="name" placeholder="请输入歌手名" />
		<a href="<%=request.getContextPath()%>/SingerServlet.do?getByName">根据歌手名查询</a><br>
		<a href="SingerUpdate.jsp">修改歌手信息</a><br>
		<a href="<%=request.getContextPath()%>/SingerServlet.do?del">删除歌手信息</a><br>
		<a href="SingerAdd.jsp">添加歌手信息</a><br>
		<%
			Singer s = (Singer)request.getAttribute("singer");
			if(s != null){
		%>
			<table>
				<tr>
					<td>ID</td>
					<td>姓名</td>
					<td>年龄</td>
					<td>性别</td>
					<td>头像</td>
					<td>图片</td>
					<td>添加时间</td>
					<td>修改时间</td>
				</tr>
				<tr>
					<td><%=s.getId()%></td>
					<td><%=s.getName()%></td>
					<td><%=s.getAge()%></td>
					<td><%=s.getSex()%></td>
					<td><%=s.getHead()%></td>
					<td><%=s.getImage()%></td>
					<td><%=s.getAddTime()%></td>
					<td><%=s.getUpdateTime()%></td>
				</tr>
		<%
			}
		%>
		</table>
	</body>
</html>