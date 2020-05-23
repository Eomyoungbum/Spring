<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>File List</title>
<link href="<c:url value='/favicon.png'/>" rel="icon" type="image/png">
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(."delete").click(function(){
		if(confirm("이 작업은 되돌릴 수 없습니다. 파일을 삭제하시겠습니까?")){
			return true;
		}else{
			return false;
		}
	})
});
</script>
</head>
<body>
<form action="/file/updateDir" method=post enctype="multipart/form-data" class="form-horizontal">
<table border="1">
<tr>
	<th>Id</th>
	<td>경로</td>
	<td>파일명</td>
	<td>크기</td>
	<td>유형</td>
	<td>날짜</td>
	<td>삭제</td>
</tr>
<c:forEach var="file" items="${fileList}">
<tr>
	<td><input type=checkbox name=fileIds value="${file.fileId}">${file.fileId}</td>
	<td>${file.directoryName}</td>
	<td><c
</table>
</form>
</body>
</html>