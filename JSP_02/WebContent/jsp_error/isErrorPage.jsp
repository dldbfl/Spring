<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ page trimDirectiveWhitespaces="true" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h2>에러가 났소! 맛좋은 에러가 여기있소!</h2>
	<h2>이건 에러 테스트용</h2>
	<p><%=exception.getMessage()%></p>
</body>
</html>