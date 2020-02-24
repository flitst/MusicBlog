<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.explorer.musicblog.pojo.SongType"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${title}</title>
		<style type="text/css">
			table {
		            border-collapse: collapse;
		            text-align:center;
		            border: none;
		            width: 100%;
		        }
		    td {
	            border:1px solid blue;
	        }
		</style>
		<script type="text/javascript">
			window.onload=function(){
				var tr = document.getElementsByClassName("tr");
				for(var i=0; i < tr.length; i++){
					tr[i].setAttribute("onmouseout","changeColor(this)");
					tr[i].setAttribute("onmouseover","changeColor(this)");
				}
			}
			
			function changeColor(e){
				if (e.style.backgroundColor=="rgb(233, 230, 230)"){
					e.style.backgroundColor="white";
				} else {
					e.style.backgroundColor="rgb(233, 230, 230)";
				}
			}
			
			function isUpdate(obj,id){
				var bool = confirm("确认修改吗？");
				if(bool){
					obj.href="TypeControlServlet.do?control=update&id="+id+"&type="+obj.title;
				}
			}
			
			function isDelete(obj,id){
				var bool = confirm("确认删除吗？");
				if(bool){
					obj.href="TypeServlet.do?control=delete&id="+id;
				}
			}
		</script>
	</head>
	<body>
		<div id="list">
			<span>${msg}</span><br>
			<c:choose>
				<c:when test="${requestScope.types != null}">
					<table>
						<tr>
							<td>ID</td>
							<td>类型</td>
							<td>创建时间</td>
							<td>修改时间</td>
							<td>操作</td>
						</tr>
						<c:forEach items="${requestScope.types}" var="type">
							<tr class="tr">
								<td>${type.getId()}</td>
								<td>${type.getType()}</td>
								<td>${type.getCreateTime()}</td>
								<td>${type.getUpdateTime()}</td>
								<td>
									<a href="javascript:void(0)" onclick="isUpdate(this,${type.getId()})" title="${type.getType()}">修改</a> | 
									<a href="javascript:void(0)" onclick="isDelete(this,${type.getId()})">删除</a>
								</td>
							</tr>
						</c:forEach>
						<tr class="tr">
							<td colspan="5" rowspan="0">
								<a href="TypeControlServlet.do?control=add">添加</a>
							<td>
						</tr>
					</table>
				</c:when>
			</c:choose>
			<hr>
		</div>
	</body>
</html>