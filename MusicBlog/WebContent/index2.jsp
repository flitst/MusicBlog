<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.explorer.musicblog.filter.WebsiteStatisticFilter" %>
<%@ page import="com.explorer.musicblog.listener.OnlineNumberListener" %>
<%@ page import="com.explorer.musicblog.servlet.CounterServlet" %>
<%@ page import="com.explorer.musicblog.util.FileUtils" %>
<%@ page import="com.explorer.musicblog.pojo.Song" %>
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
				position:relative;/*fixed;*/
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
				width:1000px;
				height:800px;
				margin:0px auto;
				background:#c4e249;
			}
			#list{
				width:40%;
				height:100%;
				overflow:auto;
				list-style:none;
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
					name.value = search.value;
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
					<input type="hidden" name="name" />
				</form>
			</div>
		</div>
		<div id="header">
			<%--
				<c:choose>
				<c:when test="${application.getAttribute(\"count\") == null || applicationScope.count == 0}">
					<c:set scope="application" var="count" >
						${application.setAttribute("count", count+=1)}
					</c:set>
				</c:when>
				<c:otherwise>
					<c:set scope="application" var="count">
						<c:if test="${session.isNew()}">
							${(Integer)applicationScope.count+=1}
						</c:if>
					</c:set>
					${application.setAttribute("count", count++)}
				</c:otherwise>
			</c:choose>
			 --%>
			<%
				Object obj = application.getAttribute("count");
				Integer count = 0;
				if(obj instanceof Integer){//Integer.class.isInstance(obj)
					count = (Integer)obj;
				}
				if(count == null || count == 0){
					//count = CounterServlet.readFile(request.getServletContext().getRealPath("/")+"count.txt");
					count = CounterServlet.readFile("D:/Java/Tomcat/apache-tomcat-9.0.0.M26/count.txt");
					if(count == 0){
						count = 1;
					}
					application.setAttribute("count", ++count);
				} else {
					if(session.isNew()){
						count+=1;
						//CounterServlet.writeFile(request.getServletContext().getRealPath("/")+"count.txt", count);
						CounterServlet.writeFile("D:/Java/Tomcat/apache-tomcat-9.0.0.M26/count.txt", count);
						application.setAttribute("count", count);
					}
				}
			%>
			<b> 站点访问次数: ${applicationScope.count}</b> | 
			<b> 在线人数: ${userNum}</b> | 
			<a id="login" href="${pageContext.request.contextPath}/UserControl.do?login">登录</a> | 
			<a id="register" href="${pageContext.request.contextPath}/UserControl.do?register">注册</a>
		</div>
	</div>
	<div id="center">
		<div id="list">
		      <ul>
		          <li>
		<%
            List<File> files = FileUtils.getFiles("D:/Java/Tomcat/apache-tomcat-9.0.0.M26/webapps/MusicBlog/resources/musics");//D:/Java/Tomcat/apache-tomcat-9.0.0.M26/upload
            if (files != null) {
                for (File file : files) {
        %>
		<span id="musics" title='<%=file%>'></span>
					<img alt="" style="width:50px;height:50px;border-radius:50%" src="${pageContext.request.contextPath}/resources/musics/imgs/offline-32717164.jpg">
		            <a href="<%=file%>">
                       <%=file.getName()%>
                    </a><br><br>
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
			window.onload = function(){
				var path = ${pageContext.request.contextPath}+"//resources//musics//";
				alert("p:"+path);
			}
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
		</script>
</html>