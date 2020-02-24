//声明全局异步刷新对象
var xhttp = null;

// 获取ID值
function getId(id) {
	return document.getElementById(id);
}

/**
 * 	请求异步方法
 * @param id		请求标签ID
 * @param uId		响应消息标签ID
 * @param reqFun 	请求方法（get/post）
 * @param url		请求地址
 * @param param		请求参数
 * @param asy		是否异步刷新
 * @returns
 */
function get(id, uId, reqFun, url, param, asy) {
	if (window.XMLHttpRequest) {
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xhttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		// code for IE6, IE5
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	} else {
		console.log("不支持XMLHTTP!");
	}
	if (xhttp != null) {
		if (reqFun != "" && reqFun.trim().length > 0 && "GET" == reqFun.toUpperCase() || "POST" == reqFun.toUpperCase()) {
			if (asy) {
				xhttp.open(reqFun, url + param, true);
			} else {
				xhttp.open(reqFun, url + param, false);
			}
		} else {
			console.log("请求方法错误!不支持get/post之外的方法");
		}
		xhttp.onreadystatechange = function(){
			if (xhttp.readyState == 4){
				if(xhttp.status == 200) {
					getId(uId).innerHTML = xhttp.responseText;
				} else {
					console.log("请求失败!");
					return;
				}
			}
		};
		xhttp.send();
	}
}

//根据ID响应信息
function responseText(id) {
	getId(id).innerHTML = xhttp.responseText;
}

function chengeText() {
	if (xhttp.readyState == 4){
		if(xhttp.status == 200) {
			responseText();
		} else {
			console.log("请求失败!");
			return;
		}
	}
};
