<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.explorer.musicblog.pojo.User" %>
<%@ page import="java.util.ArrayList" import="java.util.Iterator" import="java.util.List" import="java.util.Map" import="java.util.Map.Entry"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户管理</title>
	</head>
	<body>
		<%
			Object obj = request.getAttribute("users");
			if(obj instanceof List){
				@SuppressWarnings("unchecked")
				List<Map<String,Object>> users = (List<Map<String,Object>>)obj;
				User u = null;
				for (Map<String, Object> map : users) {
					Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
					if(iterator.hasNext()) {
						Entry<String, Object> next = iterator.next();
						String key = next.getKey();
						Object value = next.getValue();
						System.out.println("value:"+value);
						if(value instanceof User){
							u = (User)value;
		%>
		<a href="#">
		<%
		    out.println(u.getId()+"\t");
									out.println(u.getAccount()+"\t");
									out.println(u.getPwd()+"\t");
									out.println(u.getNickname()+"\t");
									out.println(u.getSignature()+"\t");
									out.println(u.getAge()+"\t");
									out.println(u.getSex()+"\t");
									out.println(u.getHead()+"\t");
									out.println(u.getImage()+"\t");
									out.println(u.getEmail()+"\t");
									out.println(u.getMobile()+"\t");
									out.println(u.getCreateTime()+"\t");
		%>
		</a>&nbsp;&nbsp;&nbsp;
		<input type="button" value="修改"/>&nbsp;&nbsp;<a href="UserManagerServlet.do?del=true&id=<%=u.getId()%>">删除</a><br>
		<%
						}
					}
					
				}
			}
		%>
		<%-- <p>
			<a>${users}</a>
		</p> --%>
		<%-- <c:forEach items="${users}" varStatus="i" var="user">
			<c:param name=""></c:param>
			<p>
				<a></a>
			</p>
		</c:forEach> --%>
	</body>
</html>