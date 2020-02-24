<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>新增歌手</title>
	</head>
	<body>
		<form action="<%=request.getContextPath()%>/SingerServlet.do?add" method="post"> <!-- enctype="multipart/form-data" -->
			<div id="list">
				姓 名: <input type="text" name="name" id="id"  placeholder="请输入姓名"/>
				年 龄: <input type="text" name="age" id="age"  placeholder="请输入年龄"/><br> 
				性 别: 
					  男<input type="radio" name="sex" value="man"/>
					  女<input type="radio" name="sex" value="lady" />
					  保密<input type="radio" name="sex" checked="checked" value="secret"/><br>
				头 像 <input type="file" id="head" name="head" onchange="showHead()"/><br>
				<img id="headImg" src=""/><br>
				图 片 <input type="file" id="image" name="image" onchange="showImage()"/><br>
				<img id="imageImg" src=""/><br>
				<input type="submit" value="新增" />
			</div>
		</form>
	</body>
</html>