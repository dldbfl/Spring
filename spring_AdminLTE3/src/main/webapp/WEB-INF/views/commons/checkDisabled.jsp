<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
	<%-- if(window.opener){
		alert("정지 당한 유저입니다.");	
		window.close();
		
		window.opener.location.href="<%=request.getContextPath()%>/commons/login";
	}else{
		alert("정지 당한 유저입니다.");
		location.href="<%=request.getContextPath()%>/commons/login";
		
	} --%>
	alert("정지된 계정입니다. \n 사용제한으로 불가합니다.");
	history.go(-1);
	
</script>   
