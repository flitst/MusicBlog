<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List,java.util.Map,java.util.Map.Entry,java.util.Iterator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.explorer.musicblog.pojo.Type"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>新增歌曲</title>
		<style type="text/css">
			#list{
				positon:absolute;
				margin-left:40%;
				margin-top:20%;
			}
		</style>
		<!-- <script type="text/javascript">
			window.location.href="TypeServlet.do";
		</script> -->
	</head>
	<body>
		<div id="list">
			<form action="SongServlet.do?song=ADD" method="post">
				<input type="text" name="name" placeholder="请输入歌曲名" /><br>
				<input type="text" name="singer" placeholder="请输入歌手名" /><br>
				<input type="text" name="lyric" placeholder="请输入歌词" /><br>
				<%
				Object obj = request.getServletContext().getAttribute("types");
				if(obj instanceof List){
					@SuppressWarnings("unchecked")
					List<Map<String,Object>> types = (List<Map<String,Object>>)obj;
				%>
					音乐类型<select id="type" name="type">
					<%
						for(Map<String,Object> map:types){
									Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
									if(iterator.hasNext()) {
										Entry<String, Object> next = iterator.next();
										String key = next.getKey();
										Object value = next.getValue();
										Type t = null;
										if(value instanceof String){
											t = new Type();
											t.setId(Integer.parseInt(key));
											t.setType(value.toString());
					%>
						<option value="<%=t.getId()%> <%=t.getType()%>"><%=t.getType()%></option>
					<%
							}
						}
					}
					%>
						</select>
				<%
				}
				%>
				<br>
				<input type="text" name="length" placeholder="请输入歌曲长度" /><br>
				<input type="submit" value="添加"/><br>
			</form>
		</div>
	</body>
</html>