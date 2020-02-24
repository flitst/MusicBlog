<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.explorer.musicblog.pojo.SongType"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>获取歌曲类型</title>
		<style type="text/css">
			#list{
				positon:absolute;
				margin-left:40%;
				margin-top:20%;
			}
		</style>
	</head>
	<body>
		<div id="list">
			<form action="TypeServlet.do" method="get">
				<input type="hidden" name="control" value="GET"/>
				<input type="submit" value="获取">
			</form>
			<%
				@SuppressWarnings("unchecked")
					List<SongType> types = (List<SongType>)request.getAttribute("types");
					if(types != null && types.size() > 0){
						for(int i = 0; i<types.size(); i++){
							out.println("<p>"+types.get(i)+"</p>");
						}
					}
			%>
		</div>
	</body>
</html>