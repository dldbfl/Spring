<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
Swal.fire(
		  'Good job!',
		  '수정이 완료 되었소!',
		  'success'
		)
	location.href="detail.do${pageMaker.makeQuery()}&bno=${board.bno}&from=modify";
</script>