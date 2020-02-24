<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.explorer.musicblog.pojo.SongType"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>上传歌曲</title>
		<!-- <script type="text/javascript" src="resources/js/webuploader-0.1.5/webuploader.js"></script>
		<script type="text/javascript" src="resources/js/webuploader-0.1.5/webuploader.min.js"></script> -->
	</head>
	<body>
		<form action="TypeServlet.do" method="get">
			<input type="hidden" name="control" value="GET"/>
			<input type="submit" value="获取音乐类型">
		</form>
		<form action="UploadServlet.do" method="post" enctype="multipart/form-data">
			 歌曲类型  <select id="type" name="type">
					<c:forEach items="${types}" var="type">
						<option value="${type.getId()} ${type.getType()}">${type.getType()}</option>
					</c:forEach>
				  </select><br>
			选择文件  <input type="file" id="file" name="uploadFile" /><br>
				  <input type="submit" value="上传"/>
		</form>
	</body>
</html>