var Remote = function(ws){
	var websocket = ws;
	var game;


	var start = function(type,dir){
		var doms = {
			gameDiv:document.getElementById("remote_game"),
			nextDiv:document.getElementById("remote_next"),
			timeDiv:document.getElementById("remote_time"),
			scoreDiv:document.getElementById("remote_score"),
			resultDiv:document.getElementById("remote_gameOver"),
		}
		game = new Game();
		game.init(doms,type,dir);
	}

	var performNext = function(nextType,nextDir){
		game.performNext(nextType,nextDir);
	}
	
	var rotate = function(){
		game.rotate();
	}
	var right = function(){
		game.right();
	}
	var down = function(){
		game.down();
	}
	var left = function(){
		game.left();
	}
	var fall = function(){
		game.fall();
	}
	var fixed = function(){
		game.fixed();
	}
	var line = function(lineNum){
		game.checkClear();
		game.addScore(lineNum);
	}
	
	var timeFunc = function(data){
		game.setTime(data);
	}
	
	var gameOver = function(){
		stop();
		game.gameOver(false);
	}
	
	var addTailLines = function(data){
		console.log(data);
		game.addTailLines(data);
	}
	
	this.rotate = rotate;
	this.right = right;
	this.down = down;
	this.left = left;
	this.fall = fall;
	this.start = start;
	this.performNext = performNext;
	this.fixed = fixed;
	this.line = line;
	this.timeFunc = timeFunc;
	this.gameOver = gameOver;
	this.addTailLines = addTailLines;
}