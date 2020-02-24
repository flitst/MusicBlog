<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${title}</title>
		<style type="text/css">
			textarea{
	        	width:400px;
	        	height:500px;
	        }
		</style>
	</head>
	<body>
		<form action="ArticleServlet.do?params=pushArticle" method="post">
			<input type="hidden" name="id" value="${user.getId()}"/>
			标题 <input type="text" name="title" /><br><br>
			引用 <input type="url" name="reference" /><br><br>
			描述 <input type="text" name="description" /><br><br>
			内容 <textarea rows="1" cols="1" style="width:400px;height:500px;" name="body"></textarea><br><br>
			<input type="submit" value="提交" /><br><br>
		</form>
	</body>
</html>