<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${title}</title>
		<script type="text/javascript">
			window.onload = function(){
				checkValue = function(){
					var input = document.getElementsByTagName("input")[2];
					if(input != null && input.value != null && input.value.trim() != ""){
						if(input.value != "<%=request.getParameter("type")%>"){
							return true;
						} else {
							alert("修改的类型不能与原类型相同！");
						}
					}
					return false;
				}
			}
		</script>
	</head>
	<body>
		<span style="color:red;font-size:16px;">${msg}</span><br><br>
		<form action="TypeServlet.do" onsubmit="return checkValue(this);" method="post">
			<input type="hidden" name="control" value="update"/>
			<input type="hidden" name="id" value="<%=request.getParameter("id")%>"/>
			<input type="text" name="type" value="<%=request.getParameter("type")%>" placeholder="请输入修改类型"/>
			<input type="submit" value="修改">
		</form>
	</body>
</html>