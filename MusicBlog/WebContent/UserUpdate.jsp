<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.explorer.musicblog.pojo.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>修改信息</title>
		<style type="text/css">
			#update{
				positon:absolute;
				margin-left:35%;
				margin-top:15%;
			}
		</style>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/asynchronism.js"></script>
	</head>
	<body>
		<div id="update">
			<form action="UserServlet.do?update" method="post">
				<span>${msg}</span>
				<b>昵称:</b>
				<input type="text" id="nickname" name="nickname"/><br> 
				<b>个性签名:</b>
				<input type="text" id="signature" name="signature"/><br>
				<b>年龄:</b>
				<input type="text" id="age" name="age"/><br>
				<b>性别:</b>
					<input type="radio" name="sex" value="1"/>男
					<input type="radio" name="sex" value="2"/>女
					<input type="radio" name="sex" value="0">保密<br>
				<b>爱好:</b>
					跑步 <input type="checkbox" name="hobby" value="跑步" />&nbsp;&nbsp;&nbsp;
					看书 <input type="checkbox" name="hobby" value="看书" />&nbsp;&nbsp;&nbsp; 
					旅游 <input type="checkbox" name="hobby" value="旅游" />&nbsp;&nbsp;&nbsp;
					游泳🏊🏻<input type="checkbox" name="hobby" value="游泳" />&nbsp;&nbsp;&nbsp;
					篮球🏀<input type="checkbox" name="hobby" value="篮球🏀" />&nbsp;&nbsp;&nbsp;
					足球⚽️<input type="checkbox" name="hobby" value="足球⚽️" />&nbsp;&nbsp;&nbsp;
					乒乓球<input type="checkbox" name="hobby" value="乒乓球" /><br/>
				头 像 <input type="file" id="head" name="head" onchange="showHead()"/><br>
				<img id="headImg" src=""/><br>
				图 片 <input type="file" id="image" name="image" onchange="showImage()"/><br>
				<img id="imageImg" src=""/><br>
				<b>邮箱</b>
				<input type="text" id = "email" name="email" onblur="checkEmail()"/><font id="userEmail" size="3px"></font><br>
				<b>手机</b>
				<input type="text" id = "mobile" name="mobile" onblur="checkMobile()" /><span id="userMobile"></span><br>
				<input type="submit" value="提交"><br> 
				<a href="User.jsp">取消修改</a>
			</form>
		</div>
	</body>
	<script type="text/javascript">
			//获取ID值
			function getId(id){
				return document.getElementById(id);
			}
			function getImg(img,url){
				img = getId(img);
				var file = img.files[0];
				var reader = new FileReader();
				url = getId(url);
				reader.readAsDataURL(file);
				reader.onload = function(e){
					url.src=this.result;
					url.width=150;
					url.height=150;
				}
			}
			function showHead(){
				getImg("head","headImg");
			}
			function showImage(){
				getImg("image","imageImg");
			}
			//根据ID响应信息
			function responseText(id){
				getId(id).innerHTML = xhttp.responseText;
			}
			function checkId() {
				get(getId("id"),getId("uId"),"GET","Update.do?id=",getId("id").value,true);
			}
			function checkPWD() {
				
			}
		</script>
</html>