var Game = function(){
	//dom元素
	var gameDiv;
	var nextDiv;
	var resultDiv;
	var scoreDiv;
	var timeDiv;

	//游戏矩阵
	var gameData = [
		[0,0,0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0,0,0],
		[0,0,0,0,0,0,0,0,0,0]
	];

	// 当前方块的类型
	var index;
	// 当前方块
	var cur;
	// 下一次即将移动的方块，用于判断是否可以移动
	var posx;
	// 下一个方块
	var next;

	//divs
	var nextDivs = [];
	var gameDivs = [];
	
	var score = 0;
	
	// 对象复制,复制之后是Object
	var cloneObject = function (obj){ 
    	return JSON.parse(JSON.stringify(obj));
	}

	// 初始化div
	var initDiv = function(container,data,divs){
		for(var j=0;j<data.length;j++){
			var divx = [];
			for(var i=0;i<data[0].length;i++){
				var newNode = document.createElement("div");
				newNode.className = 'none';
				newNode.style.top = (j*20) + 'px';
				newNode.style.left = (i*20) + 'px';
				container.appendChild(newNode);
				divx.push(newNode);
			}
			divs.push(divx);
		}
	}

	// 刷新div
	var refreshDiv = function(data,divx) {
 		for(var j=0; j<data.length;j++){
 			for(var i=0;i<data[0].length;i++){
 				if(data[j][i] == 0){
 					divx[j][i].className = 'none';
 				}else if(data[j][i] == 1){
 					divx[j][i].className = 'done';
 				}else if(data[j][i] == 2){
 					divx[j][i].className = 'current';
 				}
 			}
 		}
	}
	 // 检测点是否合法
    var check = function(pos, y, x) {
        if (pos.y + y < 0) {
            return false;
        } else if (pos.y + y >= gameData.length) {
            return false;
        } else if (pos.x + x < 0) {
            return false;
        } else if (pos.x + x >= gameData[0].length) {
            return false;
        } else if (gameData[pos.y + y][pos.x + x] == 1) {
            return false;
        } else {
            return true;
        }
    };

    // 检测数据是否合法?
    var isValid = function(pos, data) {
        for (var i = 0; i < data.length; i++) {
            for (var j = 0; j < data[0].length; j++) {
                if (data[i][j] != 0) {
                    if (!check(pos, i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

	    // 设置数据
    var setData = function() {
        for (var i = 0; i < cur.data.length; i++) {
            for (var j = 0; j < cur.data[0].length; j++) {
                if (check(cur.origin, i, j)) {
                    gameData[cur.origin.y + i][cur.origin.x + j] = cur.data[i][j];
                }
            }
        }
    };

    // 清除数据
    var clearData = function() {
        for (var i = 0; i < cur.data.length; i++) {
            for (var j = 0; j < cur.data[0].length; j++) {
                if (check(cur.origin, i, j)) {
                    gameData[cur.origin.y + i][cur.origin.x + j] = 0;
                }
            }
        }
    };
	  // 下移
    var down = function() {
        if (cur.canDown(isValid)) {
            clearData();
            cur.down();
            setData();
            refreshDiv(gameData, gameDivs);
            return true;
        } else {
            return false;
        }
    }

    // 左移
    var left = function() {
        if (cur.canLeft(isValid)) {
            clearData();
            cur.left();
            setData();
            refreshDiv(gameData, gameDivs);
        }
    }

    // 右移
    var right = function() {
        if (cur.canRight(isValid)) {
            clearData();
            cur.right();
            setData();
            refreshDiv(gameData, gameDivs);
        }
    }

    // 旋转
    var rotate = function() {
        if (cur.canRotate(isValid)) {
            clearData();
            cur.rotate();
            setData();
            refreshDiv(gameData, gameDivs);
        }
    }

	// 方块移动到底部，给它固定
	var fixed = function(){
		for(var j=0; j<cur.data.length; j++){
			for (var i = 0; i < cur.data[j].length; i++) {
				if(cur.data[j][i] >= 1){
					if(gameData[cur.origin.y + j][cur.origin.x + i] == 2){
						gameData[cur.origin.y + j][cur.origin.x + i] = 1
					}
				}
			}
		}
		refreshDiv(gameData,gameDivs);
	}

	// 检查游戏结束
	var checkOver = function(){
		var gameOver = false;
		for (var j=0; j<next.data.length; j++) {
			for(var i=0; i<next.data[j].length; i++){
				if(next.data[j][i] >= 1){
					if(gameData[0][next.origin.x + i] == 1){
						gameOver = true;
					}
				}
			}
			
		}
		return gameOver;
	}
	// 游戏结束之后的展示
	var gameOver = function(win){
		if(win){
			resultDiv.innerHTML="你赢了！";
		}else{
			resultDiv.innerHTML="你输了！";
		}
	}

	// 底部增加行
	var addTailLines = function(lines){
		for (var i = 0; i < gameData.length-lines.length; i++) {
			gameData[i] = gameData[i+lines.length];
		}
		for(var i=0; i<lines.length;i++){
			gameData[gameData.length-lines.length+i] = lines[i];
		}
		cur.origin.y = cur.origin.y - lines.length;
		if(cur.origin.y < 0){
			cur.origin.y = 0;
		}
		refreshDiv(gameData,gameDivs);
	}

	// 使用下一个方块
	var performNext = function(index,dir){
		cur = next;
		setData();
		next = new SquareFactory.prototype.make(index,dir);
		refreshDiv(gameData,gameDivs);
		refreshDiv(next.data,nextDivs);
	}
	// 消行
	var checkClear = function(){
		var line = 0;
		for(var j=0; j<gameData.length; j++){
			var clear = true;
			for(var i=0; i<gameData[j].length; i++){
				if(gameData[j][i] != 1){
					clear = false;
					break;
				}
			}
			if(clear){
				line++;
				for(var m=j;m>0; m--){
					for(var n=0; n<gameData[0].length; n++){
						gameData[m][n] = gameData[m-1][n];
					}
				}
				for(var n=0; n<gameData[0].length; n++){
					gameData[0][n] = 0;
				}
				i++;
			}
		}
		return line;
	}

	// 设置时间
	var setTime = function(time){
		timeDiv.innerHTML = time;
	}

	// 增加分数
	var addScore = function(line){
		var s=0;
		switch(parseInt(line)){
			case 1:
				s=10;
				break;
			case 2:
				s=30;
				break;
			case 3:
				s=80;
				break;
			case 4:
				s=160;
				break;
			default:
				s=200;
				break;
		}
		score = score+s;
		scoreDiv.innerHTML = score;
	}

	// 初始化
	var init = function(doms,index,dir){
		gameDiv = doms.gameDiv;
		nextDiv = doms.nextDiv;
		timeDiv = doms.timeDiv;
		scoreDiv = doms.scoreDiv;
		resultDiv = doms.resultDiv;
		index = 2;
		next = new SquareFactory.prototype.make(index,dir);
		initDiv(gameDiv,gameData,gameDivs);
		initDiv(nextDiv,next.data,nextDivs);
		refreshDiv(next.data,nextDivs);
	}

	//导出API
	this.init = init;
	this.down = down;
	this.left = left;
	this.right = right;
	this.rotate = rotate;
	this.fall = function(){
		while(down());
	};
	this.fixed = fixed;
	this.performNext = performNext;
	this.checkClear = checkClear;
	this.checkOver = checkOver;
	this.setTime = setTime;
	this.addScore = addScore;
	this.gameOver = gameOver;
	this.addTailLines = addTailLines;
}