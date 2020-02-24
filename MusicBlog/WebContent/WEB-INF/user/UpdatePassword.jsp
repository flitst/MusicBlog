<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>修改密码</title>
	</head>
	<body>
		<form action="UserUpdateServlet.do">
			旧密码<input type="text" id="oldPwd" name="old" placeholder="请输入旧密码" onblur="checkOldPwd()"/><span id="checkOld"></span><br>
			新密码<input type="text" id="newPwd" name="new" placeholder="请输入新密码" onblur="checkNewPwd()"/><span id="checkNew"></span><br>
				<input type="image" formmethod="get" onclick="check()"/>
		</form>
		<a href="javascript:history.back()">取消修改</a>
	</body>
	<script type="text/javascript">
		function getId(id){
			return document.getElementById(id);
		}
		function check(){
			var oldPwd = getId("oldPwd");
			var newPwd = getId("newPwd");
			var checkOld = getId("checkOld");
			var checkNew = getId("checkNew");
			if(oldPwd.value != ''){
				checkOld.innerText="";
			} else {
				checkOld.style.color='red';
				checkOld.innerText="账号不能为空!";
				oldPwd.onfocus();
			}
			if(newPwd.value != ''){
				checkNew.innerText="";
			} else if(oldPwd.value != newPwd.value){
				checkNew.style.color='red';
				checkNew.innerText="两次输入的密码不一样!";
				newPwd.onfocus();
			} else {
				checkNew.style.color='red';
				checkNew.innerText="密码不能为空!";
				newPwd.onfocus();
			}
		}
		/* var oldPwd = getId("oldPwd");
		var newPwd = getId("newPwd");
		function checkOldPwd(){
			var checkOld = getId("checkOld");
			if(oldPwd.value != ''){
				checkOld.innerText="";
			} else {
				checkOld.style.color='red';
				checkOld.innerText="账号不能为空!";
				oldPwd.onfocus();
			}
		}
		function checkNewPwd(){
			var checkNew = getId("checkNew");
			if(newPwd.value != ''){
				checkNew.innerText="";
			} else if(oldPwd.value != newPwd.value){
				checkNew.style.color='red';
				checkNew.innerText="两次输入的密码不一样!";
				newPwd.onfocus();
			} else {
				checkNew.style.color='red';
				checkNew.innerText="密码不能为空!";
				newPwd.onfocus();
			}
		} */
	</script>
</html>