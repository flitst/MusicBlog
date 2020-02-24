<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>后台管理</title>
		<style type="text/css">
			#list{
				position:fixed;
				margin-left:5%;
				margin-top:10%;
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
				<span>用户数: ${user_num}</span><br>
				<a href="UserServlet.do?manager">管理用户</a>
			</p>
			<p>
				<span>文章数: ${article_num}</span><br>
				<a href="ArticleControlServlet.do?manager">管理文章</a>
			</p>
			<p>
				<span>歌曲数: ${song_num}</span><br>
				<a href="SongControlServlet.do?param=get">管理歌曲</a>
			</p>
			<p>
				<span>歌曲类型数: ${song_type_num}</span><br>
				<a href="TypeControlServlet.do?param=manager">管理歌曲类型</a>
			</p>
			<p>
				<span>歌手数: ${singer_num}</span><br>
				<a href="SingerControlServlet.do?param=get">管理歌手</a>
			</p>
		</div>
	</body>
</html>