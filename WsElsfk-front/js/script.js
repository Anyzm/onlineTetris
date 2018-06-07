var trim=function(e) {
	return e.replace(/(^\s*)|(\s*$)/g,'');
}

var ChatSendEmptyContent = function() {
    setInterval(function() {
        websocket.send("");
    }, 10000);
}
var initWs = function(){
	var websocket;
	if("WebSocket" in window){
		websocket = new WebSocket(config.wsUrl+"?userId="+user.userId);
	}else{
		alert("你的浏览器不支持websocket");
	}
	var ws = new Ws();
	websocket.onopen = function(){
		console.log("websocket已连接");
		ws.count++;
		ChatSendEmptyContent();
	};
	
	//接受消息并处理
	websocket.onmessage = function(e){
		console.log(e);
		var data = JSON.parse(e.data);
		if(data.msgType == "system"){
			if(data.code == "1001"){
				local.start();
				$("#local_gameOver").html("玩家加入，游戏开始");
				$("#remote_gameOver").html("玩家加入，游戏开始");
			}
		}else if(data.msgType == "init"){
			data = data.content;
			remote.start(data.type,data.dir);
		}else if(data.msgType == "performNext"){
			data = data.content;
			remote.performNext(data.type,data.dir);
		}else if(data.msgType == "onkeydown"){
			if(data.code == "38"){
				remote.rotate();
			}else if(data.code == "39"){
				remote.right();
			}else if(data.code == "40"){
				remote.down();
			}else if(data.code == "37"){
				remote.left();
			}else if(data.code == "32"){
				remote.fall();
			}
		}else if(data.msgType == "fixed"){
			remote.fixed();
		}else if(data.msgType == "line"){
			data = data.content;
			remote.line(data.lineNum);
		}else if(data.msgType == "time"){
			data = data.content;
			remote.timeFunc(data);
		}else if(data.msgType == "gameOver"){
			remote.gameOver();
			local.gameOver();
		}else if(data.msgType == "leave"){
			$("#remote_gameover").html("对方掉线");
		}else if(data.msgType == "bottomLine"){
			local.addTailLines(data.content);
		}
	};
	websocket.onerror = function(e){
		console.log("websocket连接异常:" + e);
	};
	websocket.onclose = function(code){
		console.log("websocket连接已关闭:" + code);
		$("#local_gameover").html("已掉线");
		ws.count--;
	};
	
	return websocket
}

var setStorage = function(){
	var userId = $("#userId").val();
	var userName = $("#userName").val();
	var sex = $("#sex").find("option:selected").val();
	if(!userId || !userName || !sex || ""==trim(userId) || ""==trim(sex) || ""==trim(userName)){
		var login_check = document.getElementById("login_check");
		login_check.innerHTML="请填写完整登陆信息!";
	}
	Storage.setLocalStorage("userId", userId);
	Storage.setLocalStorage("userName", userName);
	Storage.setLocalStorage("sex", sex);
	var loginDiv = document.getElementById("cover");
	loginDiv.style.display="none";
	user.userId = userId;
	user.userName = userName;
	user.sex = sex;
	user.login();
	websocket = initWs();
	local = new Local(websocket);
	remote = new Remote(websocket);
}

// 查找房间
var findRoom = function(){
	var type = $("#findTypep").find("option:selected").attr("name");
	var value = $("#findValue").val();
	user.findRoom(type,value);
}
//创建房间
var createRoom = function(){
	user.createRoom();
}

var joinRoom = function(id){
	user.joinRoom(id);
}

var local ;
var websocket;
var remote ;
var user = new User();
var userId = Storage.getLocalStorage("userId");
var userName = Storage.getLocalStorage("userName");
var sex = Storage.getLocalStorage("sex");
if((!userId) || (!userName) || (!sex) || "undefined" == userId || "undefined" == userName || "undefined" == sex){
	user.cover();
}else if(userId || userName || sex){
	user.userId = userId;
	user.userName = userName;
	user.sex = sex;
	user.login();
	websocket = initWs();
	local = new Local(websocket);
	remote = new Remote(websocket);
}






