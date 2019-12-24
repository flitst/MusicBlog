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
		<a href="<%=request.getContextPath()%>/SingerServlet.do?getAll">查询</a>
		<table>
			<thead>
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
			</thead>
			<%
				List<Map<String,Singer>> list = (List<Map<String,Singer>>)request.getAttribute("all"); 
				if(list != null && list.size() > 0){
					Singer singer = null;
					for (Map<String, Singer> map : list) {
						Iterator<Entry<String, Singer>> iterator = map.entrySet().iterator();
						while(iterator.hasNext()) {
							singer = new Singer();
							Entry<String, Singer> next = iterator.next();
							String key = next.getKey();
							singer = next.getValue();
							
							
			%>
			<tbody>
				<tr>
					<td><%=singer.getId()%></td>
					<td><%=singer.getName()%></td>
					<td><%=singer.getAge()%></td>
					<td><%=singer.getSex()%></td>
					<td><%=singer.getHead()%></td>
					<td><%=singer.getImage()%></td>
				</tr>
			</tbody>
			<% 
						}
					}
				}
			%>
		</table>
	</body>
</html>