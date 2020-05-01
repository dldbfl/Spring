<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	int price= 1000000000;
	DecimalFormat df = new DecimalFormat("#,###");
	out.println(df.format(price));
%>
<span style="color:red;font-weight:bold;font-size:32px">
<fmt:formatNumber value="<%=price %>" pattern="#,###"></fmt:formatNumber>￦
</span>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

</body>
</html>