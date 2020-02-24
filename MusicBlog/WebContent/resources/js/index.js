function getId(id){
	return document.getElementById(id);
}
function getTagName(name){
	console.log("name:"+name);
	return document.getElementsByTagName(name);
}

function isBlank(e){
	console.log("isBlank:"+e);
	if (e == null && e.trim() == "" && e.trim().length() == 0){
		return true;
	}
	return false;
}
function isNotBlank(e){
	console.log("isNotBlank:"+e);
	if (e != null && e.trim() != "" && e.trim().length() > 0){
		return true;
	}
	return false;
}
function check(e){
	console.log("check:"+e);
	if (e != null && e.trim() != "" && e.trim().length() > 0){
		return e;
	}
	return null;
}