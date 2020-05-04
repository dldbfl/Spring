<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
$('#registBtn').on('click',function(e){
	var form=document.registForm;
	
	var files = $('input[name="uploadFile"]');
	for(var file of files){
		console.log(file.name+" : "+file.value);
		if(file.value==""){
			Swal.fire(
					  'What the File?',
					  '파일 올린거 맞나요?',
					  'question'
					)
			file.focus();
			file.click();
			return;
		}
	}
	
	if($("input[name='title']").val()==""){//form.title.value
		Swal.fire('제목은 필수 입니다.');
		$("input[name='title']").focus();
		return;
	}
	
	if(form.content.value.lenght>1000){
		Swal.fire({
			  icon: 'error',
			  title: 'Oops...',
			  text: '글자수가 1000자를 초과할 수 없어요.'
			})
		return;
	}
	
	form.submit();
});

$("#cancelBtn").on("click", function(){
	window.location.reload(true);
	window.location.href="list.do?page=1&perPageNum=10";
});

</script>





