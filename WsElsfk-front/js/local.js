var Local = function(ws){
	//websocket对象
	var websocket = ws;
	//游戏对象
	var game;
	// 时间间隔
	var INTERVAL = 1000;
	// 定时器
	var timer = null;
	// 时间计时器
	var timeCount = 0;
	// 时间
	var time = 0;

	// 绑定键盘事件
	var bindKeyEvent = function(){
		document.onkeydown = function(e){
			var msg = {};
			if(e.keyCode == 38){//up
				game.rotate();
				msg={
					"msgType":"onkeydown",
					"code":"38"
				};
			}else if(e.keyCode == 39){//right
				game.right();
				msg={
					"msgType":"onkeydown",
					"code":"39"
				};
			}else if(e.keyCode == 40){//down
				game.down();
				msg={
					"msgType":"onkeydown",
					"code":"40"
				};
			}else if(e.keyCode == 37){//left
				game.left();
				msg={
					"msgType":"onkeydown",
					"code":"37"
				};
			}else if(e.keyCode == 32){//space
				game.fall();
				msg={
					"msgType":"onkeydown",
					"code":"32"
				};
			}
			websocket.send(""+JSON.stringify(msg));
		}
	}
	// 移动
	var move = function(){
		var msg = {};
		timeFunc();
		if(!game.down()){
			game.fixed();
			msg = {"msgType":"fixed"};
			websocket.send(JSON.stringify(msg));
			var line = game.checkClear();
			if(line > 0){
				game.addScore(line);
				msg = {"msgType":"line","content":{"lineNum":""+line}};
				websocket.send(JSON.stringify(msg));
				if(line > 1){
					var data = generateBottomLine(line/2);
					msg = {"msgType":"bottomLine","content":data};
					remote.addTailLines(data);
					websocket.send(""+JSON.stringify(msg));
				}
			}
			var gameOver = game.checkOver();
			if(gameOver){
				stop();
				game.gameOver(false);
				msg = {"msgType":"gameOver","content":"you win"};
				websocket.send(JSON.stringify(msg));
				$("#remote_gameOver").html("你赢了！");
			}else{
				var nextType = generateType();
				var nextDir = generateDir()
				game.performNext(nextType,nextDir);
				msg = {"msgType":"performNext","code":"1003","content":{"dir":nextDir,"type":nextType}};
				websocket.send(""+JSON.stringify(msg));
			}
		}else{
			msg={
					"msgType":"onkeydown",
					"code":"40"
				};
			websocket.send(JSON.stringify(msg));
		}
	}

	// 计时函数
	var timeFunc = function(){
		timeCount = timeCount + 1;
		if(timeCount == 5){
			timeCount = 0;
			time = time + 1;
			game.setTime(time);
			var msg={"msgType":"time",
					 "content":""+time
					}
			websocket.send(JSON.stringify(msg));
			
		}

	}
	// 随机生成一个方块种类
	var generateType = function(){
		return Math.ceil(Math.random() * 7) - 1;
	}

	// 随机生成一个旋转次数
	var generateDir = function(){
		return Math.ceil(Math.random() * 4) - 1;
	}

	// 随机生成干扰行
	var generateBottomLine = function(lineNum){
		var lines = [];
		for(var i=0; i<lineNum;i++){
			var line = [];
			for(var j=0; j<10; j++){
				line.push(Math.ceil(Math.random()*2)-1);
			}
			lines.push(line);
		}
		return lines;
	}

	// 开始
	var start = function(){
		var doms = {
			gameDiv: document.getElementById("local_game"),
			nextDiv: document.getElementById("local_next"),
			timeDiv: document.getElementById("local_time"),
			scoreDiv: document.getElementById("local_score"),
			resultDiv: document.getElementById("local_gameOver"),
		}
		game = new Game();
		var dir = generateDir();
		var type = generateType();
		game.init(doms,type,dir);
		var msg = {"msgType":"init","code":"1002","content":{"dir":dir,"type":type}};
		websocket.send(""+JSON.stringify(msg));
		bindKeyEvent();
		var nextType = generateType();
		var nextDir = generateDir()
		game.performNext(nextType,nextDir);
		msg = {"msgType":"performNext","code":"1003","content":{"dir":nextDir,"type":nextType}};
		websocket.send(""+JSON.stringify(msg));
		timer = setInterval(move,INTERVAL);
	}
	// 结束
	var stop = function(){
		if(timer){
			clearInterval(timer);
			timer = null;
		}
		document.onkeydown = null;
	}
	
	var gameOver = function(){
		stop();
		game.gameOver(true);
	}
	
	var addTailLines = function(data){
		console.log(data);
		game.addTailLines(data);
	}
	
	// 导出API
	this.start = start;
	this.stop = stop;
	this.gameOver = gameOver;
	this.addTailLines = addTailLines;
}