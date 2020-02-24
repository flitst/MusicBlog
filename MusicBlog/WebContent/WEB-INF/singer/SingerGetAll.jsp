<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.explorer.musicblog.pojo.Singer"%>
<%@ page import="java.util.Map.Entry"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>歌手</title>
		<style type="text/css">
			table{border-collapse:collapse;}
			table,tr,th,td{border:1px solid blue}
		</style>
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
				@SuppressWarnings("unchecked")
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
					<td><%=singer.getCreateTime()%></td>
					<td><%=singer.getUpdateTime()%></td>
				</tr>
			</tbody>
			<% 
						}
					}
				}
			%>
		</table>
			<hr>
			<table>
				<%-- <c:forEach items="${all != null}" var="singers">
					<c:forEach items="${singers}" var="singer">
						<c:if test="${singer != null}">
							key:${singer.key}
							value:${singer.value}
						</c:if>
					</c:forEach>
					<tbody>
						<tr>
							<td>1</td>
						</tr>
					</tbody>
				</c:forEach> --%>
			</table>
	</body>
</html>