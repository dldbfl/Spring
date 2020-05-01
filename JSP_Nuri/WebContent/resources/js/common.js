//팝업창들 띄우기
//새로운 Window 창을 Open할 경우 사용되는 함수 (arg : 주소, 창타이틀, 넓이, 길이)
function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight) {
	winleft = (screen.width - WinWidth) / 2;
	wintop = (screen.height - WinHeight) / 2;
	var win = window.open(UrlStr , WinTitle , "scrollbars=yes,width="+ WinWidth + ", "
	+"height=" + WinHeight + ", top="+ wintop + ", left="
	+ winleft + ", resizable=yes, status=yes" );
	
win.focus();
}

//팝업창 닫기
function CloseWindow(){
	window.opener.location.reload(true);
	window.close(); 
}
//form submit
function SubmitMember(formRole){
	var uploadCheck = $('input[name="checkUpload"]').val();
	if(!(uploadCheck>0)){
		alert("사진 업로드는 필수입니다.");	
		//$('input[name="pictureFile"]').click();
		return;
	}
	var form = $('form[role="'+formRole+'"]');
	form.submit();
}


//form cancel
function Cancel(){
	history.go(-1);
}


//form enabled
function Abled(){
 	var id = $("#id").val();
  $.ajax({
      url : "checkId?id="+id,
      type : "get",
      success : function(data){
      	if(data=="SUCCESS"){            		
      		$("#idchk").text("아이디가 사용중입니다.");
      		$("#idchk").css("color", "red");
      		$("#id").css("border-color", "red");
			}else if(data=="SPACE"){
				$("#idchk").text("");
				$("#id").css("clear", "both");
			}else{
				$("#idchk").text("사용가능한 아이디입니다.");
				$("#idchk").css("color", "green");
      		$("#id").css("border-color", "green");
			}
      }
  });
}

