<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.explorer.musicblog.pojo.Song"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>查看所有歌曲</title>
	</head>
	<body>
		<a href="<%=request.getContextPath()%>/SongServlet.do?song=GETALL">获取所有歌曲</a>
		<form action="<%=request.getContextPath()%>/SongServlet.do?song=GETBYNAME" method="post">
				<p class="button">
					<input type="text" name="search" value="" placeholder="请输入歌曲名">
					<input type="submit" value="搜索" />
				</p>
		</form>
		<% 
			@SuppressWarnings("unchecked")
			List<Song> all = (List<Song>)request.getAttribute("all"); 
		%>
		<table>
		<tr>
			<td>ID</td>
			<td>歌名</td>
			<td>歌手</td>
			<td>歌词</td>
			<td>类型</td>
			<td>时长</td>
		</tr>
		<%
			if(all != null && all.size() > 0){
		%>
		<tr>
		<%
				for(int i = 0; i < all.size(); i++){
		%>
			<td> <%=all.get(i).getId()%> </td>
			<td> <%=all.get(i).getName()%> </td>
			<td> <%=all.get(i).getSinger()%> </td>
			<td> <%=all.get(i).getLyric()%> </td>
			<td> <%=all.get(i).getType()%> </td>
			<td> <%=all.get(i).getCreateTime()%> </td>
			<td> <%=all.get(i).getUpdateTime()%> </td>
		</tr>
		<%
				}
			}
		%>
		</table>
	</body>
</html>