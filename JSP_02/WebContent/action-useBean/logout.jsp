<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<%
	session.invalidate();
%>

<script>
	alert("로그아웃되었소");
	location.href="loginForm.jsp";

</script>