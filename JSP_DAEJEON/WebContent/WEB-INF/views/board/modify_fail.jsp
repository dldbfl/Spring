<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
Swal.fire({
	  icon: 'error',
	  title: 'Oops...',
	  text: '서비스 장애로 실패하였습니다!'
	})
	history.go(-1);
</script>