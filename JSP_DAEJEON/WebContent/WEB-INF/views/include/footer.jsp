<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>



  <!-- Main Footer -->
<footer class="footer">
	<div class="linkArea">
		<div class="container">
			<div class="cnt">
				<ul class="btmMenu2 pcview">
					<li><a href="/drh/DrhContentsHtmlView.do?menuSeq=3274">이용안내</a></li>
					<li><a href="/drh/DrhContentsHtmlView.do?menuSeq=2062">개인정보처리방침</a></li>
					<li><a href="/drh/DrhContentsHtmlView.do?menuSeq=1757">영상정보처리기기 운영·관리방침</a></li>
					<li><a href="/drh/board/boardNormalList.do?boardId=homepage_0001&amp;menuSeq=1507">홈페이지 개선의견</a></li>
					<!--<li><a href="/drh/DrhContentsHtmlView.do?menuSeq=4761">뷰어 다운로드</a></li>-->					
				</ul>
				<div class="siteLink">
					<span><a href="/drh/DrhContentsHtmlView.do?menuSeq=1753">실국 및 사업소/구청</a></span>
					<span><a href="/drh/DrhContentsHtmlView.do?menuSeq=1754">대전시관련</a></span>
					<span><a href="/drh/DrhContentsHtmlView.do?menuSeq=1755">유관기관</a></span>
				</div>
			</div>
		</div>
	</div>
	<div class="container cntBtm">
		<div class="fl mt22n">
			<ul class="btmMenu moview">
				<li>이용안내</li>
				<li>개인정보처리방침</li>
				<li>영상정보처리기기 운영·관리방침</li>		
				<li>홈페이지 개선의견</li>
				<!--<li><a href="/drh/DrhContentsHtmlView.do?menuSeq=4761">뷰어 다운로드</a></li>-->
			</ul>
			<address>(35242) 대전광역시 서구 둔산로 100 (둔산동)<span class="callCenter hidePc"><a href="/drh/DrhContentsHtmlView.do?menuSeq=1759">콜센터 <strong>042-120</strong></a></span></address>
			<p class="copy">(c) DAEJEON METROPOLITAN CITY. ALL RIGHTS RESERVED.</p>
		</div>
		<div class="fr">
			<dl class="callCenter forPc">
				<dt>콜센터</dt>
				<dd>
					<a href="/drh/DrhContentsHtmlView.do?menuSeq=1759"><strong>042-120</strong> <span>(365일)</span><br>
					평일 07:00~21:00<br>
					토·공휴일 09:00~18:00</a>
				</dd>
			</dl>
			<script type="text/javascript"> 
					      function certiPopup() { 
					           var sFeatures = "width=500, height=624, status=no, menubar=no, toolbar=no, location=no"; 
					           var win = window.open("", "certiFormView", sFeatures); 
					           
					           win.document.write('<html><head><title>콘텐츠제공서비스 품질인증서</title></head>');
					           win.document.write('<body>');
					           win.document.write('<img src="/images/drh/common/good.jpg" />');
					           win.document.write('</body></html>');
					           win.focus(); 
					      } 
					</script>
			<ul class="listLogo">
				<li><img src="/resources/daejeon/btm_logo01.png" alt="공공누리 오픈마크"></li>
				<li><img src="/resources/daejeon/btm_logo02.png" alt="과학기술정보통신부 WEB ACCESSIBILITY 마크(웹 접근성 품질인증 마크)"></li>
				<!-- <li><a href="#" onclick="javascript:certiPopup();return false;" title="콘텐츠제공서비스 품질인증서 새창열림"  ><img src="/images/drh/layout/common/btm_logo03.png" alt="우수콘텐츠 서비스 품질인증마크"></a></li> -->
			</ul>
		</div>
	</div>		
</footer>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->


<!-- Bootstrap 4 -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/dist/js/adminlte.min.js"></script>
<!-- common.js -->
<script src="<%=request.getContextPath() %>/resources/js/common.js"></script>
</body>
</html>







