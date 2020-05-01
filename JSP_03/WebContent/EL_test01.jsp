<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<%
	//session.invalidate();


	String str = "졸 수 있어!!";
	pageContext.setAttribute("msg", str);
	request.setAttribute("msg", "졸면 너 복습할 때 매우 화가 날거야.");
	session.setAttribute("msg", "잠 하면 이누리.");
	application.setAttribute("msg", "졸음은 일상입니다");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<ul>
		<li>스크립트릿 : <% out.println(pageContext.getAttribute("msg")); %></li>
		<li>표현식 : <%=pageContext.getAttribute("msg") %></li>
		<li>EL문 : ${sessionScope.msg}</li> 
	</ul>
</body>
</html>