<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>后台管理</title>
		<style type="text/css">
			#list{
				positon:absolute;
				margin-left:40%;
				margin-top:20%;
			}
		</style>
		<script type="text/javascript">
			
		</script>
	</head>
	<body>
		<div id="list">
			<span>${msg}</span>
			<h3>后台管理</h3>
			<p>
				<span>用户数:${num}</span><a href="UserManagerServlet.do?manager=manager">管理用户</a>
			</p>
			<p>
				<a href="Song.jsp">管理歌曲</a>
			</p>
			<p>
				<a href="Type.jsp">管理歌曲类型</a>
			</p>
			<p>
				<a href="Singer.jsp">管理歌手</a>
			</p>
		</div>
	</body>
</html>