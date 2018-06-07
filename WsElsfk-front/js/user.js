var User = function(){
	var userId;
	var userName;
	var sex;
	
	var cover = function(){
		var loginDiv = document.getElementById("cover");
		loginDiv.style.position="absolute";
		loginDiv.style.zIndex = "500";
		loginDiv.style.top="0px";
		loginDiv.style.left="0px";
		loginDiv.style.width="100%";
		loginDiv.style.height="100%";
		loginDiv.style.opacity="0.7";
		loginDiv.style.backgroundColor="gray";
		loginDiv.style.display="block";
	}
	
	var login = function(){
		var text = {"userId":this.userId,"userName":this.userName,"sex":this.sex};
		$.ajax({
			contentType:"application/json;charset=UTF-8",
			url:config.loginUrl,
			type:"POST",
			datatype:"json",
			data:JSON.stringify(text),
			success:function(e){
				if(e.code == "0"){
				}else{
					alert("登陆失败");
					console.log(e);
				}
			}
		});
	}

	var createRoom = function(){
		$.ajax({
			url:config.createRoomUrl+"?userId="+this.userId+"&open=true&password=",
			type:"GET",
			contentType:"application/json;charset=UTF-8",
			datatype:"json",
			success:function(e){
				if(e.code == "0"){
					findRoom();
					$("#local_gameOver").html("创建房间成功，等待玩家加入");
				}else{
					alert("创建房间失败");
					console.log(e);
				}
			}
		});
	}

	var findRoom = function(findType,findValue){
		if(findType && findValue){
			$.ajax({
				url:config.findRoomUrl+"?value="+findValue+"&type="+findType,
				type:"GET",
				contentType:"application/json;charset=UTF-8",
				datatype:"json",
				success:function(e){
					showRoom(e);
				}
			});
		}else{
			$.ajax({
				url:config.findAllRoomUrl,
				type:"GET",
				contentType:"application/json;charset=UTF-8",
				datatype:"json",
				success:function(e){
					showRoom(e);
				}
			});
		}
	}

	var showRoom =  function(e){
		$("#showRoom").html("");
		if(e){
			if(e.length && e.length>0){
				$("#showRoom").append("<tr><td style=\"width:200px;\">房间id</td><td style=\"width:200px;\">房主昵称</td><td style=\"width:200px;\">创建时间</td><td style=\"width:200px;\">游戏状态</td><td style=\"width:200px;\">操作</td>");
				for(var i=0;i<e.length;i++){
					var roomId = e[i].id;
					$("#showRoom").append("<tr><td style=\"width:200px;\">"+roomId+"</td><td style=\"width:200px;\">"+e[i].owner.userName+"</td><td style=\"width:200px;\">"+e[i].createTime+"</td><td style=\"width:200px;\">"+e[i].gameState+"</td><td style=\"width:200px;\"><button onclick=\"joinRoom('"+roomId+"')\">加入</button></td>")	
				}

			}else if(!e.length){
				if(e.id!="undefined" && e.id){
					var roomId = e.id;
					$("#showRoom").append("<tr><td style=\"width:200px;\">房间id</td><td style=\"width:200px;\">房主昵称</td><td style=\"width:200px;\">创建时间</td><td style=\"width:200px;\">游戏状态</td><td style=\"width:200px;\">操作</td>");
					$("#showRoom").append("<tr><td style=\"width:200px;\">"+roomId+"</td><td style=\"width:200px;\">"+e.owner.userName+"</td><td style=\"width:200px;\">"+e.createTime+"</td><td style=\"width:200px;\">"+e.gameState+"</td><td style=\"width:200px;\"><button onclick=\"joinRoom('"+roomId+"')\">加入</button></td>")	
				}
			}
		}
	}

	var joinRoom = function(id){
		$.ajax({
			url:config.joinRoomUrl+"?userId="+this.userId+"&roomId="+id,
			type:"GET",
			contentType:"application/json;charset=UTF-8",
			datatype:"json",
			success:function(e){
				console.log(e);
			}
		});
	}
	
	this.cover = cover;
	this.login = login;
	this.createRoom = createRoom;
	this.findRoom = findRoom;
	this.showRoom = showRoom;
	this.joinRoom = joinRoom;
}