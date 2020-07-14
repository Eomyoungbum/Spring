<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member ${message eq 'insert' ? 'insert' : 'update'}</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<h2>회원 정보 ${message eq 'insert' ? '입력' : '수정'}</h2>
<form action="/myapp/member/${message eq 'insert' ? 'insert' : 'update'}" method="post" onsubmit="return check();">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<table>
<tr>
<td>아이디</td><td><input type=text id=userId value="${mem.userId}" ${!empty mem ? 'readonly' : ''}><button id="idCheck">중복검사</button></td>
</tr>
<tr>
<td>이름</td><td><input type=text name=name value="${mem.name}"></td>
</tr>
<tr>
<td>비밀번호</td><td><input type=password name=password></td>
</tr>
<tr>
<td>이메일</td><td><input type=text name=email value="${mem.email}"></td>
</tr>
<tr>
<td>주소</td><td><input type=text name=address value="${mem.address}"></td>
</tr>
</table>
<input type=submit value="${message eq 'insert' ? '입력' : '수정'}"><input type=reset value=취소>
</form>
<script>
var ck = false;
$(function(){
	$("#idCheck").on("click",function(){
		if($("#userId").val()){
			$.ajaxSetup({
			    headers : {
			        'CsrfToken': $('input:hidden').val();
			    }
			});
			$.ajax({
				url : "insert/idCheck",
				type : "POST",
				data : {userId : $("#userId").val()},
				success : function(check){
					if(check){
						alert("중복되지 않는 아이디입니다.");
						$("#idCheck").remove();
						ck = !ck;
					}else{
						alert("중복됩니다. 아이디를 다시 입력해 주세요.");
					}
					return false;
				},
				error : function(){
					alert("시간이 너무 오래 걸립니다. 다시 시도해 주세요.");
					return false;
				}
			});
		}else{
			alert("아이디를 입력하세요.");
			return false;
		}
	});
})
function check(){
		if(!ck){
			alert("아이디 중복검사를 실행해 주세요.");
			return false;
		}
	}
</script>
</body>
</html>