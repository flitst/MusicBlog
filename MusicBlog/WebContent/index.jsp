<%@ page import="java.io.File"%>
<%@ page import="com.explorer.musicblog.filter.WebsiteStatisticFilter"%>
<%@ page import="com.explorer.musicblog.listener.OnlineNumberListener" %>
<%@ page import="com.explorer.musicblog.servlet.CounterServlet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			#search .text{
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
			.list img{
				width:50px;
				height:50px;
				border-radius: 50%;
			}
			.list a{
				position:relative;
				left:10px;
				top:-20px;
				text-decoration: none;
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
			<input class="text" type="text" placeholder="搜索歌曲"/>
			<p class="button">搜索</p>
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
		<blink inputmode="text">blink</blink>
		<div id="list">
			<span id="musics" title='<%=getServletContext().getContextPath()%>/resources/musics/Candy_Wind - 青空.flac'></span>
			<ul>
				<li class="list">
					<img alt="" src="<%=getServletContext().getContextPath()%>/resources/musics/imgs/offline-32717164.jpg">
					<a href="#" title="Candy_Wind - 青空.flac">Candy_Wind - 青空.flac</a>
					<%-- <a href="<%=getServletContext().getContextPath()%>/resources/musics/Candy_Wind - 青空.flac"></a> --%>
				</li>
				<li class="list">
					<img alt="" src="<%=getServletContext().getContextPath()%>/resources/musics/imgs/offline-32717172.jpg">
					<a href="<%=getServletContext().getContextPath()%>/resources/musics/Candy_Wind - 拂晓车站.flac">Candy_Wind - 拂晓车站.flac</a>
				</li>
				<li class="list">
					<img alt="" src="<%=getServletContext().getContextPath()%>/resources/musics/imgs/offline-357126.jpg">
					<a href="<%=getServletContext().getContextPath()%>/resources/musics/Cymophane - Tassel.mp3">Cymophane - Tassel.mp3</a>
				</li>
				<li class="list">
					<img alt="" src="<%=getServletContext().getContextPath()%>/resources/musics/imgs/offline-30953009.jpg">
					<a href="<%=getServletContext().getContextPath()%>/resources/musics/Alan Walker - Different World.ncm">Alan Walker - Different World.ncm</a>
				</li>
				<li class="list">
					<img alt="" src="<%=getServletContext().getContextPath()%>/resources/musics/imgs/offline-28018075.jpg">
					<a href="<%=getServletContext().getContextPath()%>/resources/musics/你是我戒不掉的烟.mp3">你是我戒不掉的烟.mp3</a>
				</li>
				<li class="list">
					<img alt="" src="<%=getServletContext().getContextPath()%>/resources/musics/imgs/offline-254574.jpg" width=50 height=50>
					<a href="<%=getServletContext().getContextPath()%>/resources/musics/一个人的夜太孤单.mp3">一个人的夜太孤单.mp3</a>
				</li>
				<li class="list">
					<img alt="" src="<%=getServletContext().getContextPath()%>/resources/musics/imgs/offline-415085696.jpg">
					<a href="<%=getServletContext().getContextPath()%>/resources/musics/像中枪一样.mp3" title="像中枪一样.mp3">像中枪一样.mp3</a>
				</li>
				<li class="list">
					<img alt="" src="<%=getServletContext().getContextPath()%>/resources/musics/imgs/offline-425570055.jpg">
					<a href="<%=getServletContext().getContextPath()%>/resources/musics/田馥甄 - 寂寞寂寞就好.mp3">田馥甄 - 寂寞寂寞就好.mp3</a>
				</li>
				<li class="list">
					<img alt="" src="<%=getServletContext().getContextPath()%>/resources/musics/imgs/offline-436487129.jpg">
					<a href="<%=getServletContext().getContextPath()%>/resources/musics/糖糖乐团 - 你抱着别的女人入睡.mp3">糖糖乐团 - 你抱着别的女人入睡.mp3</a>
				</li>
				<li class="list">
					<img alt="" src="<%=getServletContext().getContextPath()%>/resources/musics/imgs/offline-449818741.jpg">
					<a href="<%=getServletContext().getContextPath()%>/resources/musics/麦振鸿 - 仙剑奇缘.mp3">麦振鸿 - 仙剑奇缘.mp3</a>
				</li>
				<li class="list">
					<img alt="" src="<%=getServletContext().getContextPath()%>/resources/musics/imgs/offline-478507889.jpg">
					<a href="<%=getServletContext().getContextPath()%>/resources/musics/黄埔心结,零蛋呀,俗破小五郎 - 剑啸江湖.mp3">黄埔心结,零蛋呀,俗破小五郎 - 剑啸江湖.mp3</a>
				</li>
			</ul>
		</div>
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