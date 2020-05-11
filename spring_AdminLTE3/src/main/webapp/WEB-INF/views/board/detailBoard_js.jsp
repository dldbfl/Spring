<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<script>
	
	var formObj = $("form[role='form']");

	$('button#modifyBtn').on('click',function(evnet){
		//alert('modify btn click');
		formObj.attr({
			'action':'modifyForm.do',
			'method':'post'
		});
		formObj.submit();
	});
	
	$("#removeBtn").on("click", function(){
		var answer = confirm("정말 삭제하시겠습니까?");
		
	 	var srcs = $("#content img");
	 		/* $("#content img").attr("src"); */
			/* alert(srcs); */
			/* for(var src of srcs){  */
		    /* for(var i=0; i<$("#content img").length;i++){  
		    	 alert(this);
		    }  */ 
	     	$.each(srcs, function(a){
	     		
	    	var src = $(this).attr("src"); 	  
	    	
			var splitSrc= src.split("="); 
			var fileName = splitSrc[splitSrc.length-1]; 
	    	
			 var fileData = {
						fileName:fileName
				};
			 
			/* alert(fileName); */
	    	/* var fileName = fileName.split("/mvc/getImg?fileName=");  */
			deleteFile(fileData);		 
	
	    	}) 
			if(answer){		
			formObj.attr("action", "remove.do");
			formObj.attr("method", "post");
			formObj.submit();
		}  
		
		
	});
	
	$("#listBtn").on("click", function(){
		window.opener.location.reload(true);
		window.close();
	});
	
	function deleteFile(fileData) {
		$.ajax({
			url:"<%=request.getContextPath()%>/deleteImg.do",
			data : JSON.stringify(fileData),
			type:"post",
			contentType:"application/json", //보내는 data 형식 지정
			success:function(res){
				console.log(res);
			}
		});		
	}
</script>
