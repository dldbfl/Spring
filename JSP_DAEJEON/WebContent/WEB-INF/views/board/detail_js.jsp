<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<script>
	
	var formObj = $("form[role='form']");

	$("#modifyBtn").on("click",function(evnet){
		//alert('modify btn click');
		formObj.attr("action","modifyForm.do");
		formObj.submit();
	});
	
	$("#removeBtn").on("click", function(){
		var answer = confirm("정말 삭제하시겠습니까?");
		if(answer){		
			formObj.attr("action", "remove.do");
			formObj.attr("method", "post");
			formObj.submit();
		}
	});
	
	$("#listBtn").on("click", function(){
		window.location.reload(true);
		window.location.href="list.do?page=1&perPageNum=10";
	});
	

</script>
