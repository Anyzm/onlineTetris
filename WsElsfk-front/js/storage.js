(function($w) {

	if(typeof $w.Storage === 'undefined')
	var Storage = $w.Storage = {};
	var Storage = $w.Storage;
	Storage.setCookie = function(name, value){ 
		//设置名称为name,值为value的Cookie
		var expdate = new Date();   //初始化时间
		expdate.setTime(expdate.getTime() + 30 * 60 * 1000);   //时间
		document.cookie = name+"="+value+";expires="+expdate.toGMTString()+";path=/";
		//即document.cookie= name+"="+value+";path=/";   时间可以不要，但路径(path)必须要填写，因为JS的默认路径是当前页，如果不填，此cookie只在当前页面生效！~
	}
	
	Storage.getCookie = function(c_name){
		if (document.cookie.length>0){
			c_start=document.cookie.indexOf(c_name + "=");
			if (c_start!=-1){ 
				c_start=c_start + c_name.length+1;
				c_end=document.cookie.indexOf(";",c_start);
				if (c_end==-1) c_end=document.cookie.length;
				return unescape(document.cookie.substring(c_start,c_end));
			} 
		}
		return "";
	}
	
	Storage.delCookie = function(name){
		var exp = new Date();
		exp.setTime(exp.getTime() - 1);
		var cval=getCookie(name);
		if(cval!=null)
		document.cookie= name + "="+cval+";expires="+exp.toGMTString();
	}
	
	Storage.setCookie = function(name,value,time){
		if(!time){
			time = -1;
		}
		var strsec = time;
		var exp = new Date();
		exp.setTime(exp.getTime() + strsec*1);
		document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
	}
	
	Storage.setLocalStorage = function(key,value){
		localStorage.setItem(key, JSON.stringify(value));
	}
	
	Storage.getLocalStorage = function(key){
		return JSON.parse(localStorage.getItem(key));
	}
	

	
})(window);