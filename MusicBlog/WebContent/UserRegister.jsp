<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String path=request.getContextPath();%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加用户</title>
		<style type="text/css">
			body{margin:0px;padding:0px;}
			#box{
				width:200px;
				height:150px;
				margin-left:40%;
				margin-top:15%;
			}
		</style>
		<script src="https://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
		<script type="text/javascript">
			//登录前校验用户名和密码是否正确
		    /* function loginCheck(){
		    	var account = $("#account").attr("value");    	//用户名
		    	var pwd = $("#pwd").attr("value");      		//密码
		    	var datas = new Object();      		            //返回来的结果
			    $.ajax({  
	                type: "post",  
	                contentType:"application/string",
	                dataType:"json",
	                async:false,
	                url : "${base}/loginResult.htm?name="+account+"&pwd="+pwd, 
	                success: function (data) {   
	                	datas = eval("("+data+")");
	              	}   
	           	});
			    if(datas.result == "nameFalse"){                 //用户名不正确
	        		layer.tips('用户名不存在！', '#loginUser', {
	           		  tips: [2, '#FF3030'],
	           		  time: 2000
	           		});
	        		return false;
	            }else if(datas.result == "pwdFalse"){            //密码不正确
	        		layer.tips('密码不正确	！', '#loginPwd', {
	           		  tips: [2, '#FF3030'],
	           		  time: 2000
	           		});
	        		return false;
	            }else{
	            	return true;
	            }
		    } */
	    </script>
	</head>
	<body>
		<form action="" method="post" id="forms" onsubmit="return checkForm()"><!-- javascript:return check() -->
			<div id="box">
				<span>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</span><input type="text" name="account" id="id"  placeholder="请输入账号" onkeyup="checkId()"/>
				<font id="userId" size="3px"></font><span>${id}</span><br><br>
				<span>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</span><input type="password" name="pwd" id="pwd"  placeholder="请输入密码" onkeyup="checkPwd()"/>
				<span id="userPwd"><span>${pwd}</span></span><br><br>
				<span>确认密码:</span><input type="password" name="affirmPwd" id="affirmPwd"  placeholder="请确认密码" onblur="checkAffirmPwd()"/>
				<span id="userAffirmPwd"></span><span>${affirm_Pwd}</span><br><br>
				<span>手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机:</span><input type="text" id = "mobile" name="mobile"  placeholder="请输入手机号" onblur="checkMobile()" />
				<span id="userMobile"></span><span>${mobile}</span><br><br>
				<span>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱:</span><input type="text" id = "email" name="email"  placeholder="请输入邮箱" onblur="checkEmail()"/>
				<font id="userEmail" size="3px"></font><span>${email}</span><br>
				<!-- 验证方式: <input type="text" name="ur_checkway" /><br> -->
				<p align="center">
					<button id="check" onclick="checkValue()">提交</button>
				</p>
			</div>
		</form>
		<script type="text/javascript" src="<%=path%>/resources/js/asynchronism.js"></script>
		<script type="text/javascript">
			function getId(id){
				return document.getElementById(id);
			}
			<%-- 验证输入合法性 --%>
			function checkValue(){
				var account = checkEmpty("id","userId","用户名不能为空!");
				//var idExpre = "";
				if(account){
					
				}
				var pwd = checkEmpty("pwd","userPwd","密码不能为空!");
				//var pwdExpre = ";
				if(pwd){
					
				}
				var affirmPwd = checkEmpty("affirmPwd","userAffirmPwd","确认密码不能为空!");
				//var aPwdExpre = "";
				if(affirmPwd){
					
				}
				if(pwd && affirmPwd){
					if (getId("pwd").value != getId("affirmPwd").value) {
						alert("两次密码不一样,请检查!");
						getId("pwd").focus;
						getId("pwd").select;
						return false;
					}
				}
				var mobile = checkEmpty("mobile","userMobile","手机号不能为空!");
				if(mobile){
					
				}
				//var mobileExpre = "/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/";
				var email = checkEmpty("email","userEmail",);
				if(email){
					var mobileExpre = "/^\w+@\w+.\w+$/";
					var eml = getId("email");
					checkEmail(eml,mobileExpre,eml.value);
				}
				if(account && pwd && affirmPwd && mobile){
					alert("提交");
					//getId("forms").submit("UserServlet.do?register");
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
			function checkEmail(id,expre,val){
				alert("checkEmail");
				if(!expre.test(val)){//JS中正则表达式的test方法用来验证是否与该正则表达式匹配，匹配就返回true，不匹配就返回false
	            	getId(id).focus();//将焦点定位到email框
	            	getId(id).select();//选中框内全部内容
    	            return false;
	            }
			}
			function checkForm(){
				
			}
			<%-- 检查用户ID是否合法 --%>
			/* function checkAccount(){
				if (getId("id").value != null && getId("id").length > 0) {
					get(getId("id"), getId("id_msg"),"GET","UserLoginServlet.do?id=",id.value, true);
				} else {
					getId("userId").innerHTML="\"账号\"不能为空!";
				}
			}
			function checkEmail(){
				if (getId("email").value != null && getId("email").length > 0) {
					get("email",emailCallBack,"GET","addUser.do?email=",true);
				} else {
					getId("userEmail").innerHTML="'邮箱'不能为空!";
				}
			}
			function checkMobile(){
				if (getId("mobile").value != null && getId("mobile").length > 0) {
					get("mobile",mobileCallBack,"GET","addUser.do?moblie=",true);
				} else {
					getId("userMobile").innerHTML="'手机号'不能为空!";
				}
			}
			function callBack(){
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
					return true;
				} else {
					return false;
				}
			} */
			<%-- 验证用户ID回调函数 --%>
			/*function userIdCallBack() {
				if (callBack()) {
					var userId = getId("userId");
					xmlhttp.responseText = userId.value;
					if(true){
						userId.color="red";
						if("null" == xmlhttp.responseText){
							userId.innerHTML="账号不能为空!";
						}
						if("not" == xmlhttp.responseText){
							userId.innerHTML="此帐号已注册!<a href='UserLogin.jsp'>去登录</a>";
						}
					}
					if("ok" == xmlhttp.responseText){
						userId.color="green";
						userId.innerHTML=" √ ";
					}
				} else {
					alert("连接断开,请求失败!");
				}
			}*/
			<%-- 验证email回调函数 --%>
			/*function emailCallBack(){
				if(callBack()){
					var email = getId("userEmail");
					xmlhttp.responseText = email.value;
					alert(xmlhttp.responseText);
					if("not" == xmlhttp.responseText){
						email.color="red";
						email.innerHTML="此邮箱已注册!";
					}
					if("ok" == xmlhttp.responseText){
						email.color="green";
						email.innerHTML=" √ ";
					}
				} else {
					//alert("连接断开,请求失败!");
				}
			}*/
			<%-- 验证mobile回调函数 --%>
			/*function mobileCallBack(){
				if(callBack()){
					var mobile = getId("userMobile");
					xmlhttp.responseText = mobile.value;
					if("not" == xmlhttp.responseText){
						mobile.color="red";
						mobile.innerHTML="此手机号已注册!";
					}
					if("ok" == xmlhttp.responseText){
						mobile.color="green";
						mobile.innerHTML=" √ ";
					}
				} else {
					//alert("连接断开,请求失败!");
				}
			}*/
			<%-- 获取用户的请求方法/地址和是否异步刷新 --%>
			/*function getMethod(method,url,value){
				if(method.trim().length > 0 && "GET" == method.toUpperCase()){
					xmlhttp.open("GET",url,value);
					xmlhttp.send();
				} else if(method.trim().length > 0 && "POST" == method.toUperCase()){
					xmlhttp.open("POST",url,value);
				} else {
					return "请求方法错误!不支持get/post之外的方法";
				}
			}*/
			<%-- 根据用户ID/回调函数/请求的方法/请求地址/是否异步刷新,处理用户请求 --%>
			/*xmlhttp = null;
			function get(id,callback,method,url,value){
				var id = getId(id).value;
					if (window.XMLHttpRequest) {
						xmlhttp = new XMLHttpRequest();
					} else {
						xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
					}
					xmlhttp.onreadystatechange = callback;
					getMethod(method,url+id,value);
			}*/
			/* function GetO(){
			  var ajax=false;
			   try{
			    ajax = new ActiveXObject("Msxml2.XMLHTTP");
			   } catch (e){
			     try{
			      ajax = new ActiveXObject("Microsoft.XMLHTTP");
			     } catch (E) {
			      ajax = false;
			     }
			   }
			   if (!ajax && typeof XMLHttpRequest!='undefined') {
			    ajax = new XMLHttpRequest();
			   }
			   return ajax;
			} */
			/*function checkId() {
				var name = getId('id');
				var userId = getId('userId');
				if (id.value.trim().length != 0 ) {
					userId.innerHTML = '<font color="green" fontsize="20">未注册</font>';
				} else {
					userId.innerHTML = '<font color="red" fontsize="20">请输入账号</font>';
				}
			}

			function checkPwd() {
				var password = getId('password');
				var userPwd = getId('userPwd');
				if (password.value.trim().length != 0
						&& password.value == affirmPwd.value) {
					userPwd.innerHTML = '<font color="green" fontsize="20">'
							+ password.value + '</font>';
				} else if (password.value.trim().length == 0) {
					userPwd.innerHTML = '<font color="red" fontsize="20">密码不能为空!</font>';
				} else if (password.value != affirmPwd.value
						&& affirmPwd.value.trim().length != 0
						&& password.value.trim().length != 0) {
					userPwd.innerHTML = '<font color="red" fontsize="20">两次输入密码不一样</font>';
				}
			}

			function checkAffirmPwd() {
				var affirmPwd = getId('affirmPwd');
				var userAffirmPwd = getId('userAffirmPwd');
				if (affirmPwd.value.trim().length != 0
						&& affirmPwd.value == password.value) {
					userAffirmPwd.innerHTML = '<font color="green" fontsize="20">'
							+ affirmPwd.value + '</font>';
				} else if (affirmPwd.value.trim().length == 0) {
					userAffirmPwd.innerHTML = '<font color="red" fontsize="20">密码不能为空!</font>';
				} else if (password.value != affirmPwd.value
						&& affirmPwd.value.trim().length != 0
						&& password.value.trim().length != 0) {
					userAffirmPwd.innerHTML = '<font color="red" fontsize="20">两次输入密码不一样</font>';
				}
			}*/
		</script>
	</body>
</html>