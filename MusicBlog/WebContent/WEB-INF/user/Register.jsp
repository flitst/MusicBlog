<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String path=request.getContextPath();%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户注册</title>
		<style type="text/css">
			body{margin:0px;padding:0px;}
			#box{
				width:200px;
				height:150px;
				margin-left:45%;
				margin-top:15%;
			}
		</style>
	</head>
	<body>
		<form action="UserServlet.do?register" method="post" id="forms" onsubmit="javascript:return checkForm()">
			<div id="box">
				<b>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</b>
				<input type="text" name="account" id="id"  placeholder="请输入账号" onkeyup="checkId()"/><br>
				<font id="userId" size="3px"></font><span>${account_msg}</span><br>
				
				<b>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</b>
				<input type="password" name="pwd" id="pwd"  placeholder="请输入密码" /><br>
				<span id="userPwd"><span>${pwd}</span></span><br>
				
				<b>确认密码:</b>
				<input type="password" name="affirmPwd" id="affirmPwd"  placeholder="请确认密码" /><br>
				<span id="userAffirmPwd"></span><br>
				
				<p align="center">
					<button id="check">提交</button>
				</p>
			</div>
		</form>
		<script type="text/javascript" src="<%=path%>/resources/js/asynchronism.js"></script>
		<script type="text/javascript">
			<%-- 验证输入合法性 --%>
			function checkValue(){
				var account = checkEmpty("id","userId","用户名不能为空!");
				var pwd = checkEmpty("pwd","userPwd","密码不能为空!");
				var affirmPwd = checkEmpty("affirmPwd","userAffirmPwd","确认密码不能为空!");
				if(pwd && affirmPwd){
					if (getId("pwd").value != getId("affirmPwd").value) {
						console.log("两次密码不一样,请检查!");
						getId("affirmPwd").focus;
						getId("affirmPwd").select;
						return false;
					}
				}
				if(account && pwd && affirmPwd && mobile){
					return true;
				}
			}
			
			<%--非空验证--%>
			function checkEmpty(id,msgId,txt){
				var id = getId(id);
				var msg = getId(msgId);
				if(id.value != '' && id.value.length > 0){
					msg.innerText = "";
					return true;
				} else {
					msg.style.color = 'red';
					msg.innerText = txt;
					msg.focus;
					msg.select;
					return false;
				}
			}
			
			<%-- 表单提交验证 --%>
			function checkForm(){
				if(checkValue()){
					return true;
				}
				return false;
			}
			
			<%-- 异步验证账号是否存在 --%>
			var xmlhttp = null;
			function checkId(){
				if (getId("id").value != null && getId("id").length > 0) {
					get(getId("id"), getId("userId"),"GET","UserServlet.do?register&time="+Math.random()+"&account=",getId("id").value, true);
				}
				//else {
				//	getId("userId").innerHTML="<font color='red' fontsize='20'>\"账号\"不能为空!!</font>";
				//}
			}
		</script>
	</body>
</html>