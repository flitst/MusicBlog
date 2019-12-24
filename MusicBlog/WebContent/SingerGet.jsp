<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.util.TreeMap,java.util.Iterator,java.util.Map.Entry" %>
<%@ page import="com.explorer.musicblog.pojo.Singer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>歌手列表</title>
	</head>
	<body>
		<form action="GetSingerServlet.do" method="get">
			<input type="submit" value="获取">
		</form>
		<%
			Object obj = request.getAttribute("all");
			if(obj != null && obj instanceof List){
				List<Map<String,Singer>> list = (List<Map<String,Singer>>)obj;
				for(Map<String,Singer> tm : list){
					Iterator<Entry<String, Singer>> itr = tm.entrySet().iterator();
					if(itr.hasNext()){
						Entry<String, Singer> entry = itr.next();
						String key = entry.getKey();
						Singer singer = entry.getValue();
		%>
			<a href="#">
					<%
						out.println("歌手id:"+key);
						out.println("歌手:"+singer);
					%>
			</a><br>
		<%
					}
				}
			}
		%>
	</body>
</html>