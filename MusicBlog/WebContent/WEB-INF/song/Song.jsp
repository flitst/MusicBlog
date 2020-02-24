<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.explorer.musicblog.pojo.Song"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>歌曲</title>
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
	</head>
	<body>
		<a href="SongServlet.do?song=all">获取所有歌曲</a>
		<form action="SongServlet.do?song=name" method="post">
				<p class="button">
					<input type="text" name="search" value="" placeholder="请输入歌曲名">
					<input type="submit" value="搜索" />
				</p>
		</form>
		<table>
			<tr>
				<td>ID</td>
				<td>歌名</td>
				<td>歌手</td>
				<td>歌词</td>
				<td>类型</td>
				<td>创建时间</td>
				<td>修改时间</td>
				<td>操作</td>
			</tr>
			<c:if test="${all != null}">
				<c:forEach items="${all}" var="all">
					<tr onmouseover="changeColor(this)" onmouseout="changeColor(this)">
						<td> ${all.getId()} </td>
						<td> ${all.getName()} </td>
						<td> ${all.getSinger()} </td>
						<td> ${all.getLyric()} </td>
						<td> ${all.getType()} </td>
						<td> ${all.getCreateTime()} </td>
						<td> ${all.getUpdateTime()} </td>
						<td>
							<a href="javascript:void(0)" onclick="isUpdate(this,${all.getId()})" title="${all}">修改</a> | 
							<a href="javascript:void(0)" onclick="isDelete(this,${all.getId()})">删除</a>
						</td>
					</tr>
				</c:forEach>
				<tr class="tr" onmouseover="changeColor(this)" onmouseout="changeColor(this)">
					<td colspan="8" rowspan="0">
						<a href="SongControlServlet.do?control=add">添加</a>
					<td>
				</tr>
			</c:if>
		</table>
		<script type="text/javascript">
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
					obj.href="SongControlServlet.do?control=update&id="+id+"&value="+obj.title;
				}
			}
			
			function isDelete(obj,id){
				var bool = confirm("确认删除吗？");
				if(bool){
					obj.href="SongServlet.do?control=delete&id="+id;
				}
			}
		</script>
	</body>
</html>