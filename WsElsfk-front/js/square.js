var Square = function(){
	//方块数据
	this.data = [
		[0,0,0,0],
		[0,0,0,0],
		[0,0,0,0],
		[0,0,0,0]
	];

	//方块类型
	this.index = 0;

	// 原点
	this.origin = {
		x:0,
		y:0
	};

	// 方向
	this.dir = 0;
	
}

// 是否可以旋转
Square.prototype.canRotate = function(isValid) {
    var d = (this.dir + 1) % 4;
    var test = [
        [0, 0, 0, 0],
        [0, 0, 0, 0],
        [0, 0, 0, 0],
        [0, 0, 0, 0]
    ];
    for (var i = 0; i < this.data.length; i++) {
        for (var j = 0; j < this.data[0].length; j++) {
            test[i][j] = this.rotates[d][i][j];
        }
    }
    return isValid(this.origin, test);
}
// 旋转
Square.prototype.rotate = function(num){
	if(!num){
		num = 1;
	}
	this.dir = (this.dir + num) % 4;
	this.data = JSON.parse(JSON.stringify(this.rotates[this.dir]));
	return this;
}

//克隆方法
Square.prototype.clone = function(){
	var c = new SquareFactory.prototype.make(this.index,this.dir);
	c.origin.x = this.origin.x;
	c.origin.y = this.origin.y;
	c.dir = this.dir;
	return c;
}

// 是否可以下降
Square.prototype.canDown = function(isValid) {
    var test = {};
    test.y = this.origin.y + 1;
    test.x = this.origin.x;
    return isValid(test, this.data);
}
// 下降
Square.prototype.down = function(){
	this.origin.y = this.origin.y + 1;
}

// 是否可以左移
Square.prototype.canLeft = function(isValid) {
    var test = {};
    test.y = this.origin.y;
    test.x = this.origin.x - 1;
    return isValid(test, this.data);
}
// 左移
Square.prototype.left = function(){
	this.origin.x = this.origin.x - 1;
}

// 是否可以右移
Square.prototype.canRight = function(isValid) {
    var test = {};
    test.y = this.origin.y;
    test.x = this.origin.x + 1;
    return isValid(test, this.data);
}
// 右移
Square.prototype.right = function(){
	this.origin.x = this.origin.x + 1;
}
