<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<%
	String msg = "너무 강렬한 빛! 그저 빛!";
	out.println("<h1> header.jsp : "+request.getParameter("msg")+"</h1>");
%>