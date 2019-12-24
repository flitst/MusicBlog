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
			<h3>后台管理</h3>
			<span>用户数:${num}</span><a href="UserManagerServlet.do?manager=manager">管理用户</a>
		</div>
	</body>
</html>