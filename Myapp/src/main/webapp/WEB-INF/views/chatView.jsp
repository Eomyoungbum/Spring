<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<h2>안녕하세요 ${pageContext.session.id}님.</h2>
<h1>채팅방</h1>
<p id=chatroom>채팅을 시작해 주세요.</p>
<br>
<input type=text id=inputchat><br>
<button id=open>채팅시작</button>
<button id=send>보내기</button>
<button id=finish>채팅종료</button>
<script>
$(function(){
	var ws; 
	$("#open").on("click",function(){
		if(ws==null | ws==undefined){
			ws = new WebSocket("ws://"+location.host+"<c:url value='/websocket/chat.do/websocket' />");
			ws.onopen = function(){
				$("#chatroom").text("웹 소켓이 열렸습니다. 채팅을 시작하셔도 됩니다.");
				$("#open").hide();
				setTimeout(function(){
					$("#chatroom").text("");
				},1000);
			};
		}else{
			$("#chatroom").text($("#chatroom").text()+"웹 소켓이 이미 실행중입니다.");
		}
	});
	$("#send").on("click",function(){
		if(ws==null | ws==undefined){
			alert("웹 소켓이 연결되지 않았습니다.");
		}else{
			ws.send($("#inputchat").val());
			$("#inputchat").val("");
			ws.onmessage = function(event){
				var message = $("#chatroom").text().replace(/\n/g,'<br>');
				message += ("\n"+event.data);
				$("#chatroom").text(message);
			};
		}
	});
	$("#finish").on("click",function(){
		if(ws==null | ws==undefined){
			alert("웹 소켓이 연결되지 않았습니다.");
		}else {
			ws.close();
			ws.onclose = function(event){
				$("#chatroom").text("채팅이 종료됩니다.");
				$("#open").show();
			}
			ws=null;
		}
	});
});
</script>
</body>
</html>