<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>主页</title>
		<style type="text/css">
			body {
				margin: 0px;
				padding: 0px;
				font-size: 12px;
				font-family: "楷体";
			}
			#navHead{
				position: fixed;
				top: 0;
				left: 0;
				z-index: 999;
				width: 100%;
			}
			.modal-open{
				overflow: hidden;
			}
			#fieldset_div input{
				margin-top: 25px;
			}
			#main {
				width: 600px;
				height: 420px;
				margin: 0px auto;
				background-color: #f5f5f5;
			}
			
			#form_fieldset {
				border-style: none;
				margin: 100px 0px 0px 0px;
			}
			
			#commit {
				width: 100px;
				height: 30px;
				border-style: none;
				margin: 90px 0px 0px 60px;
				background-color: red;
			}
			
			#fieldset_div {
				width: 500px;
				height: 300px;
				margin: -10px 0px 0px 180px;
			}
			#loginInfo{
				margin:20px 0px 0px 215px;
			}
			#fieldset_div span {
				font-size: 16px;
				color: red;
			}
			#info {
				color:red;
				float:right;
				margin: 0px 5px 0px 0px;
			}
		</style>
		<script type="text/javascript">
			function getId(id) {
				return document.getElementById(id);
			}
			window.onload = function() {
				getId("loginId").value = "请输入注册账号";
				getId("phone").value = "不支持填写区号";
				getId("email").value = "填写要绑定邮箱";
				// 账号获取焦点
				getId("loginId").onfocus = function() {
					if (this.value == "请输入注册账号") {
						this.value = "";
					}
				}
				// 手机获取焦点
				getId("phone").onfocus = function() {
					if (this.value == "不支持填写区号") {
						this.value = "";
					}
				}
				// 邮箱获取焦点
				getId("email").onfocus = function() {
					if (this.value == "填写要绑定邮箱") {
						this.value = "";
					}
				}
				var loginIdMeg = getId("loginIdMeg");
				getId("loginId").onblur = function() {
					if (this.value.length == 0) {
						this.value = "请输入注册账号";
						loginIdMeg.style.color = "red";
						loginIdMeg.innerHTML = "请输入注册账号";
					} else {
						if (this.value.length < 4 || this.value.length > 45) {
							loginIdMeg.style.color = "red";
							loginIdMeg.innerHTML = "<br/>账号长度不能少于4个或多余45个字符!";
						} else {
							var reg = /^([([\u4E00-\u9FA5\\s]+)|([a-zA-Z_]+]|\w+|.+){0,45}$/;
							if (reg.test(this.value)) {
								loginIdMeg.style.color = "limegreen";
								loginIdMeg.innerHTML = " √ 账号效验通过!"
							} else {
								loginIdMeg.style.color = "red";
								loginIdMeg.innerHTML = "X 以字母/汉字/下划线开头!";
							}
						}
					}
				}
				var loginPwd_span = getId("loginPwd_span");
				getId("loginPwd").onblur = function() {
					if (this.value == "") {
						loginPwd_span.style.color = "red";
						loginPwd_span.innerHTML = "请输入密码";
					} else if (this.value.length < 8) {
						loginPwd_span.style.color = "red";
						loginPwd_span.innerHTML = "密码长度不能少于8位!";
					} else if (this.value.length <= 10) {
						var reg = /^[a-zA-Z_]+[0-9a-z]*$/;
						if (reg.test(this.value)) {
							loginPwd_span.style.color = "limegreen";
							loginPwd_span.innerHTML = "密码较弱!";
						}
					} else if (this.value.length <= 12) {
						var reg = /^[a-zA-Z_]+[a-zA-Z0-9]*$/;
						if (reg.test(this.value)) {
							loginPwd_span.style.color = "limegreen";
							loginPwd_span.innerHTML = "密码较强!";
						}
					} else {
						var reg = /^[a-zA-Z_]+\S*$/;
						if (reg.test(this.value)) {
							loginPwd_span.style.color = "limegreen";
							loginPwd_span.innerHTML = "密码很强!";
						}
					}
				}
				var loginPwd1_span = getId("loginPwd1_span");
				getId("loginPwd1").onblur = function() {
					if(getId("loginPwd1".val() == null)){
						loginPwd1_span.style.color = "red";
						loginPwd1_span.innerHTML = "请输入密码";
					}
					if(getId("loginPwd").val() != getId("loginPwd1").val()){
						loginPwd_span.style.color = "red";
						loginPwd_span.innerHTML = "\"密码\"和\"验证密码\"不同,请查看";
					}
				}
				var viewPWD1 = getId("btn");
				viewPWD1.onmousedown=function(){
					getId("loginPwd").type="number";
				}
				viewPWD1.onmouseup=function(){
					getId("loginPwd").type="password";
				}
				var viewPWD1 = getId("btn1");
				viewPWD1.onmousedown=function(){
					getId("loginPwd1").type="number";
				}
				viewPWD1.onmouseup=function(){
					getId("loginPwd1").type="password";
				}
				var email_span = getId("email_span");
				getId("email").onblur = function() {// /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/
					var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
					if (this.value == "") {
						this.value = "填写要绑定邮箱";
					} else if (reg.test(this.value)) {
						email_span.style.color = "limegreen";
						email_span.innerHTML = "此邮箱可以注册!";
					} else {
						email_span.style.color = "red";
						email_span.innerHTML = "<br/>请输入正确的邮箱格式!格式为:××@××.××";
					}
				}
				var phone_span = getId("phone_span");
				getId("phone").onblur = function() {// /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/
					var reg = /^1[34578]\d{9}$/;
					if (this.value == "") {
						this.value = "不支持填写区号";
					} else {
						if (reg.test(this.value)) {
							phone_span.style.color = "limegreen";
							phone_span.innerHTML = "号码效验通过!";
						} else {
							phone_span.style.color = "red";
							phone_span.innerHTML = "请输入正确的手机号码!";
						}
					}
				}
			}
		</script>
	</head>
	<body>
		<div id="main">
				<form action="CheckUserName.do" method="post">
					<fieldset id = "form_fieldset">
						<h3 id="loginInfo">请输入注册信息</h3>
						<div id="fieldset_div">
							账&nbsp;&nbsp;号&nbsp;
							<input type="text" id = "loginId" name="loginId" value="" />
							<span id="loginIdMeg">*</span><br/>
							密&nbsp;&nbsp;码&nbsp;
							<input type="password" id = "loginPwd" name="loginPwd" value="" />
							<input type="button" id="btn" value="显示密码"/><br/>
							<span id="loginPwd_span">*</span><br/> 
							确 认 密 码<input type="password" id = "loginPwd1" name="affirmPwd" value="" />
							<input type="button" id="btn1" value="显示密码"/><br/>
							<span id="loginPwd1_span">*</span><br/> 
							年龄<input type="text" name="age" value="" /><br/> 
							性&nbsp;&nbsp;别&nbsp;&nbsp;
							<input type="radio" name="sex" value="男"/>男&nbsp;&nbsp;&nbsp;
							<input type="radio" name="sex" value="女" />女&nbsp;&nbsp;&nbsp;
							<input type="radio" name="sex" value="然而不想填" checked/>然而不想填<br/> 
							
							爱好
							跑步 <input type="checkbox" name="hobby" value="跑步" />
							看书 <input type="checkbox" name="hobby" value="看书" /> 
							旅游 <input type="checkbox" name="hobby" value="旅游" /> 
							游泳🏊🏻<input type="checkbox" name="hobby" value="游泳" /> 
							篮球🏀<input type="checkbox" name="hobby" value="篮球🏀" /> 
							足球⚽️<input type="checkbox" name="hobby" value="足球⚽️" /> 
							乒乓球<input type="checkbox" name="hobby" value="乒乓球" /><br/> 
							
							头像<input type="file" id = "head" name="head" ></input>
							图片<input type="file" id = "image" name="image" ></input>
							
							邮&nbsp;&nbsp;箱&nbsp;
							<input type="text" id = "email" name="email" />
							<span id = "email_span"></span><br/>
							
							手&nbsp;&nbsp;机&nbsp;
							<input type="text" id = "phone" name="mobile"   />
							<span id = "phone_span"></span><br/>
							<input type="submit" id = "commit" value="提交注册信息" name = "commit()"/><br/>
							<span id = "commit_span"></span><br/>
						</div>
					</fieldset>
				</form>
				<p id="info"> * 为必填项&nbsp;</p>
		</div>
	</body>
</html>