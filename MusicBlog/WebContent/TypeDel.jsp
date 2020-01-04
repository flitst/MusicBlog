<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>删除歌曲类型</title>
	</head>
	<body>
		<%
			String crl = request.getParameter("control");
			out.print("del:"+crl);
			if(crl != null && crl.length() > 0){
		%>
			<form action="TypeServlet.do">
				<input type="hidden" name="control" value="DELETE"/>
				<input type="hidden" name="id" value="${id}"/>
				<input type="submit" value="删除">
			</form>
		<%		
			}
		%>
	</body>
</html>