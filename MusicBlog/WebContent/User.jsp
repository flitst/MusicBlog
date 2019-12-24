<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.explorer.musicblog.pojo.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>我的信息</title>
		<style type="text/css">
        table {
            border-collapse: collapse;
            border: none;
            width: 100%;
        }
        td {
            border:1px solid blue;
        }
        #center{
        	positon:absolute;
			margin-left:35%;
			margin-top:15%;
        }
        </style>
	</head>
	<body>
			<%
				out.println(request.getAttribute("msg"));
			%>
			<%
				Object key = request.getSession().getAttribute("ukey");
				if(key != null && key instanceof String) {
					String ukey = (String)key;
					Object obj = request.getSession().getAttribute(ukey);
					User u = new User();
					if(obj instanceof User) {
						u = (User)obj;
			%>
		<table>
				<tr>
					<td>ID</td>
					<td>账号</td>
					<td>昵称</td>
					<td>个性签名</td>
					<td>年龄</td>
					<td>性别</td>
					<td>爱好</td>
					<td>头像</td>
					<td>图片</td>
					<td>邮箱</td>
					<td>手机</td>
					<td>注册时间</td>
				</tr>
				<tr>
					<td> <% out.println(u.getId());  %> </td>
					<td> <% out.println(u.getAccount()); %> </td>
					<td> <% out.println(u.getNickname()); %> </td>
					<td> <% out.println(u.getSignature()); %> </td>
					<td> <% out.println(u.getAge()); %> </td>
					<td>
						 <% 
						 	if(0 == (byte)u.getSex()){
							 	out.println("保密"); 
						 	} else if(1 == (byte)u.getSex()){
							 	out.println("男"); 
						 	} else if(2 == (byte)u.getSex()){
							 	out.println("女"); 
						 	}
						 %> 
					</td>
					<td> <% out.println(u.getHobby()[0].toString()); %> </td>
					<td> <% out.println(u.getHead()); %> </td>
					<td> <% out.println(u.getImage()); %> </td>
					<td> <% out.println(u.getEmail()); %> </td>
					<td> <% out.println(u.getMobile()); %> </td>
					<td> <% out.println(u.getRegisterTime()); %> </td>
				</tr>
			<%
					} else {
						out.println("没有此用户!");
					}
				}
			%>
		</table>
		<hr/>
		<div id="controlPanel">
			<a href="UserServlet.do?update">修改信息</a>
			<a href="UserServlet.do?updatePwd">修改密码</a>
			<a href="UserServlet.do?logout">注销</a>
		</div>
		<hr/>
		<input type="file" formaction="miltper-data"/>
	</body>
</html>