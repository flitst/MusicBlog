<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>修改密码</title>
	</head>
	<body>
		<form action="UserServlet.do?forgetpwd" method="get" onsubmit="return check(this)">
			<input type="text" id="no" name="no" placeholder="请输入手机号或邮箱" onblur="checkNo()"/><span id="no_msg"></span><br>
			<input type="submit"  value="提交"/>
			<!-- input type="image" formmethod="get" /> -->
		</form>
	</body>
	<script type="text/javascript">
		function getId(id){
			return document.getElementById(id);
		}
		function check(){
			var no = getId("no");
			var msg = getId("no_msg");
			if(no != null && no.value != "" && no.value.trim() != ""){
				msg.innerText="";
				return true;
			}
			return false;
		}
		function checkNo(){
			var no = getId("no");
			var msg = getId("no_msg");
			if(no != null && no.value != "" && no.value.trim() != ""){
				msg.innerText="";
				return true;
			}
			msg.style.color='red';
			msg.innerText="请输入手机号或邮箱！";
			no.onfocus();
			return false;
		}
	</script>
</html>