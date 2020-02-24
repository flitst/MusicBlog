<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.explorer.musicblog.filter.WebsiteStatisticFilter" %>
<%@ page import="com.explorer.musicblog.listener.OnlineNumberListener" %>
<%@ page import="com.explorer.musicblog.servlet.CounterServlet" %>
<%@ page import="com.explorer.musicblog.util.FileUtils" %>
<%@ page import="com.explorer.musicblog.pojo.Song" %>
<%@ page import="java.io.FileOutputStream"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.util.List" %>
<%@ page import="java.io.File" %>
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
			input{
				border:none;
				outline:none;
				background:none;
			}
			#top{
				width:100%;
				height:50px;
				position:absolute;
				background:#00000030;/*#efebeb*/
			}
			#search{
				width:450px;
				height:35px;
				padding-top: 1%;
				margin:0px auto;
			}
			#search div{
				margin:-10px auto;
			}
			#search .text{
				width:100%;
				height:35px;
				font-size:20px;
				line-height:35px;
				font-family:'kaiti';
				border-radius: 15px;
				border: 1px solid blue;
			}
			#search .button{
				width:60px;
				height:35px;
				line-height:35px;
				margin:-40px 465px;
    			text-align: center;
				border-radius: 15px;
				border: 1px solid blue;
				cursor:pointer;
			}
			#header{
				float:right;
				margin:-35px 10px;
			}
			#center{
				width:1500px;
				height:800px;
				margin:0px auto;
				background:#c4e249;
			}
			#list{
				width:30%;
				height:100%;
				overflow-x:hidden;
				overflow-y:auto;
				list-style:none;
			}
			#control{
				width:400px;
				height:50px;
				margin:-70px auto;
				position:relative;
				line-height:50px;
				background:#d8f36b;
			}
			#down{
				width:100%;
				height:50px;
				position:fixed;
				background:#efebeb;
			}
			#list ul{
				width:100%;
				height:100%;
				margin-left: -30px;
    			margin-top: 10px;
    		}
			#list ul li img{
				width:50px;
				height:50px;
				border-radius: 50%;
			}
			#list ul li a{
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
		<script type="text/javascript">
			window.onload = function(){
				checkValue = function(){
					var search = document.getElementsByTagName("input")[0];
					if(search != null && search.value != null && search.value.trim() != ""){
						return true;
					}
					return false;
				}
			}
		</script>
	</head>
<body>
	<div id="top">
		<div id="search">
			<div>
				<form action="${pageContext.request.contextPath}/SongServlet.do?song=getByName" method="post" onsubmit="return checkValue(this);">
					<input class="text" type="text" name="search" placeholder="搜索歌曲"/>
					<p class="button">
						<input class="btn" type="submit" value="搜索" />
					</p>
				</form>
			</div>
		</div>
		<div id="header">
			<c:choose>
				<c:when test="${applicationScope.count == null || applicationScope.count == 0}">
					<c:set scope="application" var="count">
						${count+=1}
					</c:set>
				</c:when>
				<c:otherwise>
						<c:if test="${applicationScope.count == 0}">
							<c:set scope="application" var="count" value="${count = CounterServlet.readFile(\"D:/Java/Tomcat/apache-tomcat-9.0.0.M26/count.txt\")}">
									<c:if test="${count == 0}">
										<c:set scope="application" var="count">
											${count = 1}
										</c:set>
									</c:if>
							</c:set>
						</c:if>
						<c:if test="${session.isNew()}">
							<c:set scope="application" var="count">${count+=1}</c:set>
						</c:if>
				</c:otherwise>
			</c:choose>
			<b> 站点访问次数: ${applicationScope.count}</b> | 
			<b> 在线人数: ${applicationScope.userNum}</b> | 
			<c:choose>
				<c:when test="${sessionScope.user != null}">
					欢迎你：<a id="user" href="javascript:void(0);" onclick="getUser()">${user.getAccount()}</a>
				</c:when>
				<c:otherwise>
					<a id="login" href="${pageContext.request.contextPath}/UserControlServlet.do?params=login">登录</a> | 
					<a id="register" href="${pageContext.request.contextPath}/UserControlServlet.do?params=register">注册</a> | 
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	
	<div id="center">
		<div id="list">
		      <ul>
		          <li>
					<%	
			            List<File> files = FileUtils.getFiles("D:/Java/Tomcat/apache-tomcat-9.0.0.M26/webapps/MusicBlog/resources/musics");
			            if (files != null) {
			                for (File file : files) {
			            			
			        %>
								<span id="musics" title='<%=file%>'></span>
								<img alt="" style="width:50px;height:50px;border-radius:50%" src="${pageContext.request.contextPath}/resources/musics/imgs/offline-32717164.jpg">
					            <a href="<%=file%>>">
			                       <%=file.getName()%>
			                    </a><br>
			                    <br>
					<%
					      }
					  }
					%>
		          </li>
		      </ul>
			<%-- <c:choose>
					<c:when test="${requestScope.songs != null and requestScope.songs.size() > 0}">
						<ul>
							<c:forEach items="${songs}" var="item">
								<li>
									<img alt="" style="width:50px;height:50px;border-radius:50%" src="${pageContext.request.contextPath}/resources/musics/imgs/offline-32717164.jpg">
									<a href="<%=file%>">
										${item.getName()}
										${item.getSinger()}
									</a>
								</li>
							</c:forEach>
						</ul>
					</c:when>
					<c:otherwise>
						<c:if test="${requestScope.name != null}">
							没有与“<font color='red' size='5px'><c:out value="${requestScope.name}"></c:out></font>”类似的歌曲哦!
							${request.removeAttribute("keyword")}
							${request.removeAttribute("name")}
						</c:if>
					</c:otherwise>
				</c:choose>
		 	--%>
		</div>
		
		<div>
			<audio src="" id="src">
				<source class="musics" src="/MusicBlog/resources/musics/麦振鸿 - 仙剑奇缘.mp3">
				<source class="musics" src="/MusicBlog/resources/musics/像中枪一样.mp3">
				<source class="musics" src="/MusicBlog/resources/musics/田馥甄 - 寂寞寂寞就好.mp3">
				<source class="musics" src="/MusicBlog/resources/musics/赵聪 - 琵琶语.mp3">
				<source class="musics" src="/MusicBlog/resources/musics/Candy_Wind - 青空.flac">
				<source class="musics" src="/MusicBlog/resources/musics/Candy_Wind - 拂晓车站.flac">
			</audio>
			<a href="#">a</a>
		</div>
		
		<div id="control">
			<audio id="music">
				<source src="">
			</audio>
			<button id="prevPlay">上一首</button>
			<button id="playMusic" onclick="play();">播放</button>
			<button id="nextPlay">下一首</button>
			<button id="stopMusic" onclick="stop();">停止</button>
			<button id="anewMusic" onclick="afresh();">重播</button>
			<select id="playPattern" >
				<option value="order">顺序播放
				<option value="list">列表循环
				<option value="simple">单曲循环
				<option value="random">随机播放
			</select>
		</div>
	</div>
	<div id="down">
		<div id="copyright">
			<a href="RootControlServlet.do?params=contact">联系站长</a>&nbsp;&nbsp;&nbsp;<span> www.explorer.com ©copyright 2019 - ~ </span>
		</div>
	</div>
</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/index.js"></script>
	<script type="text/javascript">
		getUser = function (){
			console.log("getUser()");
		}
		// 获取歌曲列表
		musics = function(){
			var musics = document.getElementsByClassName("musics");
			var audios = new Array();
			for (var i=0; i<musics.length; i++){
		    	var src = musics[i].src;
		    	audios[i] = src;
		    	console.log(audios[i]);
			}
			return audios;
		}();
		
		var index = 0; // 当前播放音乐索引
		
		// 给“上一首”添加一个监听事件
		getId("prevPlay").addEventListener("click",prevPlay.call(this));
		
		// 给“下一首”添加一个监听事件
		getId("nextPlay").addEventListener("click",nextPlay.call(this));
		
		// 顺序播放
		orderPlay = function(){
			for (var i=0; i<musics.length; i++){
				if(index < musics.length){
			    	audio = new Audio(musics[index++]);
				} else {
					index = 0;
				}
			}
			return audio;
		}
		// 列表播放
		listPlay = function(){
			for (var i=0; i<musics.length; i++){
				if(index < musics.length){
			    	audio = new Audio(musics[index++]);
				} else {
					index = 0;
				}
			}
			return audio;
		}
		// 单曲循环
		simplePlay = function(){
			var bool = true;
			if(bool){
		    	audio = new Audio(musics[index]);
		    	//afresh;
		    	if(audio.src != null){
					audio.pause();
					audio.load();
					audio.play();
				}
			} else {
				
			}
			for (var i=0; i<musics.length; i++){
				if(index < musics.length){
			    	audio = new Audio(musics[index]);
				} else {
					index = 0;
				}
			}
			return audio;
		}

		// 随机播放
		randomPlay = function(){
			var ran = Math.floor(Math.random() * musics.length);
			for (var i=0; i<musics.length; i++){
		    	audio = new Audio(musics[ran]);
			}
			return audio;
		}
		
		//var musics = getId("src");//getId("musics");
		var playId = getId("playMusic");
		var status = "paused";
		var list = getId("list");
		//var audio = getId("src");
		var audio = document.createElement("audio");
		//var source = document.createElement("source");
		//list.appendChild(audio);
		//audio.appendChild(source);
		//source.src=musics.src;
		var pattern = getId("playPattern").value;
		switch(pattern){
			// 顺序播放
			case "order":
				console.log("order");
				audio = orderPlay(audio);
				break;
			// 列表循环
			case "list":
				console.log("list");
				audio = listPlay(audio);
				break;
			// 单曲循环
			case "simple":
				console.log("simple");
				audio = simplePlay(audio);
				break;
			// 随机播放
			case "random":
				console.log("random");
				audio = randomPlay(audio);
				break;
			default :
				console.log("没有这种播放模式！");
		}
		
		// 播放上一首
		function prevPlay(){
			console.log("上一首");
		}
		
		// 播放音乐
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
		
		// 播放暂停
		stop = function(){
			if(audio.src != null){
				audio.pause();
				audio.crrentTime=0;
				audio.load();
				playId.innerText="播放";
			}
		}
		
		// 播放下一首
		function nextPlay(){
			console.log("下一首");
		}
		
		// 重新播放
		afresh = function(){
			if(audio.src != null){
				audio.pause();
				audio.load();
				audio.play();
			}
		}
	</script>
</html>