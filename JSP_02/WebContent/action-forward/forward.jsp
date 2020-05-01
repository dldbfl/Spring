<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="8kb" autoFlush="true"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
	alert("target.jsp 페이지로 이동합니다.");
</script>

<%-- <!--야매방법  -->
<jsp:forward page="/action-forward/target.jsp?name=홍길동&age=12"></jsp:forward>
 --%>
 
 
<!--정식  -->
<jsp:forward page="/action-forward/target.jsp">
	<jsp:param value='<%=URLEncoder.encode("이유리","utf-8") %>'
				name="name"/>
	<jsp:param value="27" name="age"/>

</jsp:forward> 
