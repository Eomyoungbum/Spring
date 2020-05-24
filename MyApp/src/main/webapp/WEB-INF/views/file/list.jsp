<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
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
	$(".delete").click(function(){
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
		<c:set var="len" value="${fn:length(file.fileName)}"/>
		<c:set var="fileType" value="${fn:toUpperCase(fn:substring(file.fileName, len-4, len))}" />
			<c:if test="${(fileType eq '.JPG') or (fileType eq '.JPEG') or (fileType eq '.PNG') or (fileType eq '.GIF')}">
				<td>
				<img src='<c:url value="/img/${file.fileId}"/>' width="100" class="img-thumbnail"><br>
				<td>
			</c:if>
			<td>
			<a href='<c:url value="/pds/${file.fileId}" />'>${file.fileName}</a><br>
			</td>
		<td>
			<fmt:formatNumber value="${file.fileSize/1024}" pattern="#,###" />KB
		</td>
		<td>${file.fileContentType}</td>
		<td>${file.fileUploadDate}</td>
		<td>
			<a href='<c:url value="/file/delete/${file.fileId}" />' class="delete">삭제</a>
		</td>
		</tr>
	</c:forEach>
</table>
선택한 파일을 <select name="directoryName">
	<option value="/">/
	<option value="/images">이미지
	<option value="/data">자료실
	<option value="/spring">스프링
	<option value="/commons">공통
</select>로 <input type=submit value="이동"><p>
<a href='<c:url value="/file/new" />'>업로드</a>
</form>
</body>
</html>