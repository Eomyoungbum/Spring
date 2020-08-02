<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<button id=btn1>JSON 객체 전송</button>
<!--  <button id=btn2>jQuery 전송</button>-->
<div id=result></div>
<script>
var jsonVO = {name:"엄영범",age:20};
$("#btn1").on("click",function(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState === xhr.DONE){
			if(xhr.status === 200 || xhr.status === 201){
				document.getElementById("result").innerText=xhr.responseText;
			}
		}
	}
	xhr.open('post','json/ex');
	xhr.setRequestHeader("content-type","application/json");
	xhr.setRequestHeader("${_csrf.headerName}","${_csrf.token}");
	xhr.send(JSON.stringify(jsonVO));
});
/*$('#btn2').on("click",function(){
	$.ajax({
		async : "true",
		url : "json/ex",
		type : "post",
		contentType : "application/json",
		data : {"name" : "abc", "age" : 31, "${_csrf.parameterName}" : "${_csrf.token}"},
		dataType : "json",
		success : function(result){
			$("#result").text(result);
		},
		error : function(error){
			alert(error.statusText);
		}
	});
});*/
</script>
</body>
</html>