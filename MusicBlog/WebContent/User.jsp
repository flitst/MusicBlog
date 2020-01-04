<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.explorer.musicblog.pojo.User,java.io.*,com.explorer.musicblog.utils.FileUtil"%>
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
	        body table tr td img {
	        	width:50px;
	        	height:50px;
	        	border-radius:50%;
	        }
        </style>
	</head>
	<body>
		${msg}
		<%
			String key = (String)request.getSession().getAttribute("ukey");
			if(key != null && key instanceof String) {
				String ukey = (String)key;
				Object obj = request.getSession().getAttribute(ukey);
				User u = new User();
				if(obj instanceof User) {
					u = (User)obj;
					if(u != null){
		%>
		<table>
				<tr>
					<td>ID</td>
					<td>头像</td>
					<td>账号</td>
					<td>昵称</td>
					<td>个性签名</td>
					<td>年龄</td>
					<td>性别</td>
					<td>爱好</td>
					<td>图片</td>
					<td>邮箱</td>
					<td>手机</td>
					<td>注册时间</td>
					<td>修改时间</td>
				</tr>
				<tr>
					<td> <% out.println(u.getId());  %> </td>
					<td><img alt="头像" src="<%=u.getHead()%>"/></td>
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
					<td> 
						<% 
							String[] hobby = u.getHobby();
							if(hobby != null && hobby.length > 0){
								for(int i=0; i<hobby.length; i++){
									out.print(hobby[i].toString());
								}
							}
						 %>
					</td>
					<td> 
						<%
						FileUtil.getFiles(u.getImage());
						File file = new File(u.getImage());
						out.println("file:"+file);
						File[] files = file.listFiles();
						out.println("files:"+files);
						if(files != null && files.length > 0){
							for(int i=0; i<files.length; i++){
								out.println("path:"+files[i].getPath());
						%> 
								<img alt="用户图片" src="<%=files[i].getPath()+files[i].getName()%>">
						<%
							}
						}
						%>
					</td>
					<td> <% out.println(u.getEmail()); %> </td>
					<td> <% out.println(u.getMobile()); %> </td>
					<td> 
					     <%
						     out.println(u.getCreateTime());
						 %> 
				    </td>
					<td> 
					     <%
						     out.println(u.getUpdateTime());
						 %> 
				    </td>
				</tr>
		</table>
			<%
						}
					} else {
							out.println("没有查询到用户信息!");
						out.println("没有此用户!");
					}
				}
			%>
		<hr/>
		<div id="controlPanel">
			<a href="UserServlet.do?update">修改信息</a>
			<a href="UserUpdatePWD.jsp">修改密码</a>
			<a href="UserServlet.do?logout">退出登录</a>
		</div>
		<hr/>
		<form action="UploadServlet.do" method="post">
			<input type="file" formaction="miltper-data"/>
			<input type="submit" value="上传歌曲" />
		</form>
	</body>
</html>