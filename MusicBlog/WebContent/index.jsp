<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.explorer.musicblog.filter.WebsiteStatisticFilter"%>
<%@ page import="com.explorer.musicblog.listener.OnlineNumberListener" %>
<%@ page import="com.explorer.musicblog.servlet.CounterServlet" %>
<%@ page import="com.explorer.musicblog.pojo.Song"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.File"%>
<!DOCTYPE html>
<html>
	<head>
		<!-- <meta  http-equiv="refresh"  content="15">  -->
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>音乐盒子</title>
		<style type="text/css">
			body{
				margin:0px;
				padding:0px;
				font-family:'kaiti';
			}
			#top{
				width:100%;
				height:50px;
				position:fixed;
				background:#00000030;/*#efebeb*/
			}
			#search{
				width:450px;
				height:35px;
				margin:5px auto;
			}
			#search form .text{
				width:100%;
				height:100%;
				font-size:20px;
				font-family:'kaiti';
			}
			#search .button{
				width:60px;
				height:35px;
				cursor:pointer;
				line-height:35px;
				margin:-40px 465px;
    			text-align: center;
				border-radius: 15px;
				border: 1px solid blue;
			}
			#header{
				margin:-35px 10px;
			}
			#center{
				width:800px;
				height:500px;
				padding-top:55px;
				margin:0px auto;
				background:#c4e249;
			}
			#list{
				width:350px;
				height:100%;
				overflow:auto;
			}
			#control{
				width:200px;
				height:50px;
				position:relative;
				left:50%;
				bottom:50px;
				background:#d8f36b;
			}
			#down{
				width:100%;
				height:50px;
				background:#efebeb;
			}
			#list ul{
				width:100%;
				height:100%;
				margin-left: -30px;
    			margin-top: 10px;
			}
			.list{
				width:100%;
				height:70px;
				list-style:none;
			}
			.list ul li img{
				width:50px;
				height:50px;
				border-radius: 50%;
			}
			.list ul li a{
				position:relative;
				left:10px;
				top:-20px;
				text-decoration:none;
			}
			#copyright{
				width:100%;
				height:50px;
				line-height:50px;
				text-align:center;
			}
		</style>
	</head>
<body>
	<div id="top">
		<div id="search">
			<form action="<%=request.getContextPath()%>/SongServlet.do?song=GETBYNAME" method="post">
				<input class="text" type="text" name="search" placeholder="搜索歌曲"/>
				<p class="button">
					<input type="submit" value="搜索" />
				</p>
			</form>
		</div>
		<div id="header">
			<%
				int count = 0;
				if(application.getAttribute("count") == null){
					count = CounterServlet.readFile(request.getServletContext().getRealPath("/")+"count.txt");
					application.setAttribute("count", count);
				}
				count = (Integer)application.getAttribute("count");
				if(session.isNew()){
					count++;
					out.println("newSession:"+count);
				}
				application.setAttribute("count", count);
				CounterServlet.writeFile(request.getServletContext().getRealPath("/")+"count.txt", count);
			%>
			<b> 站点访问次数: <%=count%></b>
			<b> 在线人数: ${application.count}</b>
			<a id="login" href="UserLogin.jsp">登录</a>
			<a id="register" href="UserRegister.jsp">注册</a>
		</div>
	</div>
	<div id="center">
		<bgsound loop="infinite" src="<%=getServletContext().getContextPath()%>/resources/musics/Candy_Wind - 拂晓车站.flac">
		<div id="list">
		<% 
			@SuppressWarnings("unchecked")
			List<Song> all = (List<Song>)request.getAttribute("songs");
		%>
			<%
				if(all != null && all.size() > 0){
			%>
			<ul>
			<%
				for(int i = 0; i < all.size(); i++){
			%>
				<li>
					<img alt="" style="width:50px;height:50px;border-radius:50%" src="<%=getServletContext().getContextPath()%>/resources/musics/imgs/offline-32717164.jpg">
					<a href="<%=getServletContext().getContextPath()%>/resources/musics/Candy_Wind - 拂晓车站.flac" style="position:relative;left:10px;top:-20px;text-decoration:none">
						<%=all.get(i).getName()%>
						<%=all.get(i).getSinger()%>
					</a>
				</li>
			<%
					}
				}
			%>
			</ul>
		</div>
		<span id="musics" title='<%=getServletContext().getContextPath()%>/resources/musics/Candy_Wind - 青空.flac'></span>
		<div id="control">
			<audio id="music">
				<source src="">
			</audio>
			<button id="playMusic" onclick="play();">播放</button>
			<button id="stopMusic" onclick="stop();">停止</button>
			<button id="anewMusic" onclick="afresh();">重播</button>
		</div>
	</div>
	<div id="down">
		<div id="copyright">
			<span> www.explorer.com ©copyright 2019 - ~ </span>
		</div>
	</div>
</body>
	<script type="text/javascript">
			/* window.onload = function(){ */
				 function getId(id){
					return document.getElementById(id);
				}
				//var a = getId("list").getElementsByTagName("a");
				//var musics = getId("musics");
				//var control = getId("control");
				//var audio = new Audio();
				<%-- audio.src="<%=getServletContext().getContextPath()%>/resources/musics/"; --%>
				//alert(audio.src);
				/* var lists = new Array();
				for(var i = 0;i<a.length;i++){
					lists[i] = center[i];
					musics.src = lists[i];
					console.log(musics.src);
				} */
				var musics = getId("musics");
				var playId = getId("playMusic");
				var status = "paused";
				var list = getId("list");
				var audio = document.createElement("audio");
				var source = document.createElement("source");
				list.appendChild(audio);
				audio.appendChild(source);
				var a = document.getElementsByTagName("a");
				source.src=musics.title;
				play = function(){
					if(audio.src != null){
						if(audio.paused){
							audio.play();
							playId.innerText="暂停";
						} else if(!audio.paused){
							audio.pause();
							playId.innerText="播放";
						}
					}
				}
				/* playId.addEventListener("playing",function(){
				status = "playing";
				music.play();
				playId.innerText="暂停";
				alert(status);
				});
				playId.addEventListener("pause",function(){
					status = "paused";
					music.pause();
					playId.innerText="播放";
					alert(status);
				}); */
				stop = function(){
					if(audio.src != null){
						audio.pause();
						audio.crrentTime=0;
						audio.load();
						playId.innerText="播放";
					}
				}
				afresh = function(){
					if(audio.src != null){
						audio.pause();
						audio.load();
						audio.play();
					}
				}
			//}
		</script>
</html>