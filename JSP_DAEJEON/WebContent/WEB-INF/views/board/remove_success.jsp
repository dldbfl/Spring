<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
Swal.fire(
		  'Good job!',
		  '삭제가 성공하였소!',
		  'success'
		)
	window.location.href="list.do?page=1&perPageNum=10";
</script>