<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>上传歌曲</title>
		<!-- <script type="text/javascript" src="resources/js/webuploader-0.1.5/webuploader.js"></script>
		<script type="text/javascript" src="resources/js/webuploader-0.1.5/webuploader.min.js"></script> -->
	</head>
	<body>
		<form action="UploadServlet.do" method="post" enctype="multipart/form-data">
			选择文件:<input type="file" id="file" name="uploadFile" />
					<input type="submit" value="上传"/>
		</form>
	</body>
</html>