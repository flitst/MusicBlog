<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.explorer.musicblog.pojo.SongType,java.util.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${title}</title>
	</head>
	<body>
		<span style="color:red;font-size:16px;">${msg}</span><br><br>
		<form action="TypeServlet.do" method="post" >
			<input type="hidden" name="control" value="GET"/>
			<input type="submit" value="获取歌曲类型">
		</form>
		<%
			@SuppressWarnings("unchecked")
			List<SongType> types = (List<SongType>)request.getAttribute("types");
			if(types != null && types.size() > 0){
				for(SongType t : types){
		%>
					<p><%=t.getType()%></p>
		<%
				}
			}
		%>
		<form action="TypeServlet.do">
			<input type="hidden" name="control" value="ADD"/>
			<input type="text" name="type" placeholder="请输入类型"/>
			<input type="submit" value="添加">
		</form>
	</body>
</html>