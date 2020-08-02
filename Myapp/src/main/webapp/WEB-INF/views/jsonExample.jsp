<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<span id=ajaxarea>ajax 값</span>
	<br>
	<button id=ajax>ajax 실행</button>
	<script>
		$("#ajax").click("on", function() {
			/** var xhr = new XMLHttpRequest();
			var setArea = function(word) {
				document.getElementById("ajaxarea").innerText = word;
			}
			xhr.open('get', 'json/example?userId=gctserf');
			xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
			xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
			xhr.send();
			xhr.onreadystatechange = function() {
				if (xhr.readyState === xhr.DONE) {
					if(xhr.status === 200 || xhr.status === 201){
					setArea(xhr.responseText);
					}
				}
			} **/
			$.ajax({
			url : "json/example",
			type : "post",
			data : {userId : "gctserf", "${_csrf.parameterName}" : "${_csrf.token}"},
			dataType : "text",
			success : function(result){
				$("#ajaxarea").text(result);
			},
			error : function(e){
				console.log(e.statusText);
			}
		});
		});
	</script>
</body>
</html>