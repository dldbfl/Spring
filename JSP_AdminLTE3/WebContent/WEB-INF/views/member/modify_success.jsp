<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
	alert("수정 완료")
	location.href="detail.do?id=${id}";
	window.opener.location.reload(true);
	window.close();
</script>

