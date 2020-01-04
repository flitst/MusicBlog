<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.explorer.musicblog.pojo.Type"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Type</title>
		<style type="text/css">
			#list{
				positon:absolute;
				margin-left:40%;
				margin-top:20%;
			}
		</style>
		<script type="text/javascript">
			window.onload = function(){
				isDel = function(){
					var bool = confirm("确认删除吗？");
					if(bool){
						var del = document.getElementById("delete");
						var dele = document.getElementById("dele");
						//alert("val:"+del.value)
						//alert("title:"+dele.title)
						dele.href="TypeServlet.do?control=delete&id="+dele;
					}
				}
			}
		</script>
	</head>
	<body>
		<div id="list">
			<span>${msg}</span>
			<span style="color:red;font-size:16px;">${msg}</span><br><br>
			<form action="TypeServlet.do" method="get" >
				<input type="hidden" name="control" value="GET"/>
				<input type="submit" value="获取歌曲类型">
			</form>
			<%	
				@SuppressWarnings("unchecked")
				List<Type> types = (List<Type>)request.getAttribute("types");
				if(types != null && types.size() > 0){
					for(int i = 0; i<types.size(); i++){
			%>
					<p>
						<%
							out.println(types.get(i).getId()+"\t"+types.get(i).getType());
						%>
						<a href="TypeUpdate.jsp?control=update&id=<%=types.get(i).getId()%>&type=<%=types.get(i).getType()%>">修改</a> | 
						<input type="button" id="delete" onclick="isDel()" value="<%=types.get(i).getId()%>"/>
						<a href="#" id="dele" onclick="isDel()" title="<%=types.get(i).getId()%>">删除</a>
					</p>
			<%
					}
				}
			%>
		</div>
	</body>
</html>