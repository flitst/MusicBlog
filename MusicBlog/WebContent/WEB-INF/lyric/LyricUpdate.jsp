<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>添加歌词</title>
	</head>
	<body>
		<form action="<%=request.getContextPath()%>/LyricServlet.do?update" method="post"> <!-- enctype="multipart/form-data" -->
			<div id="list">
				 I D : <input type="text" name="id" id="id"  placeholder="请输入歌词ID"/><br>
				作曲人: <input type="text" name="song" id="song"  placeholder="请输入作曲人"/><br>
				作词人: <input type="text" name="lyric" id="lyric"  placeholder="请输入作词人"/><br> 
				演唱者: <input type="text" name="singer" id="singer"  placeholder="请输入演唱者"/><br> 
				内  容: <input type="text" name="content" id="content"  placeholder="请输入内容"/><br> 
				<input type="submit" value="修改" />
			</div>
		</form>
	</body>
</html>