<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>File Home</title>
</head>
<body>
<p><a href='<c:url value="/file/new"/>'>업로드</a></p>
<p><a href='<c:url value="/file/list"/>'>파일 전체 목록</a></p>
<p><a href='<c:url value="/file/list/"/>'>루트 디렉토리 목록</a></p>
<p><a href='<c:url value="/file/list/spring"/>'>스프링 디렉토리 목록</a></p>
<p><a href='<c:url value="/file/gallery"/>'>갤러리</a></p>
</body>
</html>