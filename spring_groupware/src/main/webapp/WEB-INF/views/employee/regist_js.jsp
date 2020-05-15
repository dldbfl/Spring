<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<!-- inline scripts related to this page end -->

<script type="text/javascript">

$('input#picture').on('change', function(event){
	/* $('input[name="checkUpload"]').val(0); */
	
	var fileFormat = this.value.substr(this.value.lastIndexOf(".") + 1).toUpperCase();
	
	//이미지 확장자 jpg확인
	if(fileFormat != "JPG"){
		alert("이미지는 jpg형식만 가능합니다");
		return;
	}
	//이미지파일 용량체크
	if(this.files[0].size > 1024*1024*1){
		alert("사진 용량은 1MB이하만 가능합니다.");
		return;
	};
	
	/* document.getElementById('inputFileName').value = this.files[0].name; */
	
	if(this.files && this.files[0]){
		var reader = new FileReader();
		reader.onload = function(e){
			//이미지 미리보기
			$('div#picturePreView').css({'background-image' : 'url(' + e.target.result + ')',
				'background-position' : 'center', 'background-size' : 'cover',
				'background-repeat' : 'no-repeat'
			});
		}
	reader.readAsDataURL(this.files[0]);
	}
});

$('#directInput').on('change', function(event){
	$('select[name="email"]' ).prop("disabled", true);
});

function CheckID(){
   	var id = $('input[name="id"]').val();
    $.ajax({
        url : "checkId?id="+id,
        type : "get",
        success : function(data){
        		/* $("#idchk").text("아이디가 사용중입니다.");
        		$("#idchk").css("color", "red"); */
        		alert("아이디가 사용중입니다");
        		$("#id").val('');
        		$("#id").css("border-color", "red");
				},
		error:function(error){
				/* $("#idchk").text("사용가능한 아이디입니다.");
				$("#idchk").css("color", "green"); */
				alert("사용가능한 아이디입니다.");
        		$("#id").css("border-color", "green");
        }
    });
 }
 
  
 
 
function goSubmit(cmd){ 
	var frm = document.getElementById("registForm");
	switch(cmd) {
		case "post":				 
			//if (!docSubmit()) return;
			$(window).unbind("beforeunload");
			controlSubmit(registForm);
			//frm.submit();
			break;
		case "close":
			if(confirm("편집중인 문서는 저장되지 않습니다 !\n창을 닫으시겠습니까 ?")){
				window.close();
			}
			return;
			break;
		default:
			return;
			break;
	}
}


</script>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function SearchAddress(){
	    new daum.Postcode({
	        oncomplete: function(data) {
	        	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postCode').value = data.zonecode;
                document.getElementById("address[0]").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("address[1]").focus();
	        }
	    }).open();		
	}
</script>








