<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.explorer.musicblog.pojo.Type"%>
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
			<form action="TypeServlet.do">
				<input type="submit" value="获取">
			</form>
		</div>
	</body>
</html>