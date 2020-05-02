<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<script>
	
	var formObj = $("form[role='form']");

	$("#registBtn").on("click",function(event){
		formObj.attr("action","registForm.do");
		formObj.submit();
	});
	$(".detailBtn").on("click", function(){
		formObj.attr("action", "detail.do");
		formObj.submit();		
	});
	
</script>
