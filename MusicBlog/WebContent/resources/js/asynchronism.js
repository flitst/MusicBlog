//声明全局异步刷新对象
xhttp = null;
// 获取ID值
function getId(id) {
	return document.getElementById(id);
}
// 根据ID响应信息
function responseText(id) {
	getId(id).innerHTML = xhttp.responseText;
}
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
		xhttp.onreadystatechange = function() {
			// console.log(this.readyState + this.status);
			if (this.readyState == 4 && this.status == 200) {
				getId("uId").innerHTML = xhttp.responseText;
			} else {
				console.log("请求失败!");
				return;
			}
		};
		if (reqFun != "" && reqFun.trim().length > 0
				&& "GET" == reqFun.toUpperCase()) {
			if (asy) {
				xhttp.open(reqFun, url + param, true);
			} else {
				xhttp.open(reqFun, url + param, false);
			}
			xhttp.send();
		} else if (reqFun != "" && reqFun.trim().length > 0
				&& "POST" == reqFun.toUpperCase()) {
			if (asy) {
				xhttp.open(reqFun, url + param, true);
			} else {
				xhttp.open(reqFun, url + param, false);
			}
		} else {
			console.log("请求方法错误!不支持get/post之外的方法");
		}
	}
}