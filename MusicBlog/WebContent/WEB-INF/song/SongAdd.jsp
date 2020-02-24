<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List,java.util.Map,java.util.Map.Entry,java.util.Iterator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.explorer.musicblog.pojo.SongType"%>
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
			<form action="SongServlet.do?song=add" method="post" enctype="multipart/form-data">
		      选择歌曲  <input type="file" name="file"/><br>
				<input type="text" name="name" placeholder="请输入歌曲名" /><br>
				<input type="text" name="singer" placeholder="请输入歌手名" /><br>
				<input type="text" name="lyric" placeholder="请输入歌词" /><br>
		      歌曲类型  <select id="type" name="type">
					<c:forEach items="${types}" var="type">
						<option value="${type.getType()}">${type.getType()}</option>
					</c:forEach>
				</select>
				<br>
				<input type="submit" value="添加"/><br>
			</form>
		</div>
	</body>
</html>