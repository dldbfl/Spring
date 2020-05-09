<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h3>Uploaded File Infomation</h3>
	<ul>
		<li>title: ${title} </li>
		<li>original File Name: ${originalFileName} </li>
		<li>uploaded File Name: ${uploadedFileName} </li>
		<li>upload Path: ${uploadPath} </li>
	</ul>
	<a href="javascript:history.go(-1);" >
		<button type="button">뒤로가기</button>
	</a>
</body>
</html>