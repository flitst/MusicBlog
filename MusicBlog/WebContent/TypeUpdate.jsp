<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>歌曲类型修改</title>
	</head>
	<body>
		<span style="color:red;font-size:16px;">${msg}</span><br><br>
		<form action="TypeServlet.do" method="get" >
			<input type="hidden" name="control" value="GET"/>
			<input type="submit" value="获取歌曲类型">
		</form>
		<%
			String crl = request.getParameter("control");
			out.print("update:"+crl);
			if(crl != null && crl.length() > 0){
				
		%>
			<form action="TypeServlet.do">
				<input type="hidden" name="control" value="UPDATE"/>
				<input type="hidden" name="id" value="${id}"/>
				<input type="text" name="type" value="${type}" placeholder="请输入修改类型"/>
				<input type="submit" value="修改">
			</form>
		<%		
			}
		%>
	</body>
</html>