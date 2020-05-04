<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
	<title>대전시-자유게시판</title>
	<style>
		table th,td{
			text-align:center;
		}
		
	
	</style>
</head>	
	
<body>
  <c:set var="pageMaker" value="${dataMap.pageMaker }" />	  	
   <div class="content-wrapper" >
   
   <jsp:include page="content_header.jsp">
		<jsp:param value="대전시-자유게시판" name="subject"/>
		<jsp:param value="list.do" name="url"/>
		<jsp:param value="목 록" name="item"/>
	</jsp:include> 
	   

    <!-- Main content -->
    <section class="content">
		<div class="card">
			<div class="card-header with-border">
				<button type="button" class="btn btn-primary" id="registBtn">자료등록</button>
				<p>총<font color="purple" size="4px"> &nbsp; ${fn:length(dataMap.boardList)}</font>&nbsp;건 &nbsp;|&nbsp;&nbsp;&nbsp;<font color="purple" size="4px">${pageMaker.cri.page}/${pageMaker.realEndPage} </font>&nbsp;페이지</p>
				<div id="keyword" class="card-tools" style="width:350px;">	
					<div class="input-group row">
						
						<select class="form-control" name= "searchType" id = "searchType">
							<option ${pageMaker.cri.searchType eq 'tcw' ? 'selected' : '' } value = "tcw">전 체</option>
							<option ${pageMaker.cri.searchType eq 't' ? 'selected' : '' } value = "t">제 목</option>
							<option ${pageMaker.cri.searchType eq 'w' ? 'selected' : '' } value = "w">작성자</option>
							<option ${pageMaker.cri.searchType eq 'c' ? 'selected' : '' } value = "c">내 용</option>
							<option ${pageMaker.cri.searchType eq 'tc' ? 'selected' : '' } value = "tc">제목+내용</option>
							<option ${pageMaker.cri.searchType eq 'cw' ? 'selected' : '' } value = "cw">작성자+내용</option>
						</select>
					
						<input  class="form-control" type="text" name="keyword" placeholder="검색어를 입력하세요." accept="utf-8" value="${param.keyword }"/>
						<span class="input-group-append">
							<button class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search" onclick="searchList_go(1);" >
								<i class="fa fa-fw fa-search"></i>
							</button>
						</span>
					</div>						
				</div>			
			</div>
			<div class="card-body">
				<table class="table table-bordered text-center">
					<tr style="font-size:0.95em;">
						<th style="width:8%;">번 호</th>
						<th style="width:48%;">제 목</th>
						<th style="width:12%;">작성자</th>
						<th>등록일</th>
						<th style="width:7%;">조회수</th>
						<th style="width:8%;">파일여부</th>
					</tr>
					<c:if test="${empty dataMap.boardList }">
						<tr>
							<td colspan="6">
								<strong>해당 내용이 없습니다.</strong>
							</td>
						</tr>
					</c:if>
					<c:forEach items="${dataMap.boardList }" var ="board">
						<tr>
							<td>${board.bno }</td>
							<td id="boardTitle" style="text-align:left;max-width: 100%;
							overflow: hidden; whith-space: nowrap; text-overflow: ellipsis;">
							<a class ="detailBtn" href="detail.do${pageMaker.makeQuery(pageMaker.cri.page) }&bno=${board.bno }&state=list">
								<span class="col-sm-12">${board.title } &nbsp;&nbsp;&nbsp;<font color="purple" size="2px"> +${board.replycnt }</font></span>
							</a>
						</td>
						<td>${board.writer }</td>
						<td>
							<fmt:formatDate value="${board.regDate }" pattern="yyyy-MM-dd"/>
						</td>
						<td>
							<span class="badge bg-purple">${board.viewcnt }</span>
						</td>						
						<td>
							<c:if test="${!empty board.attachList }"> 
								 <div class="badge">
									  <img src="<%=request.getContextPath()%>/resources/images/file.png" style="width:20px;height:20px;"> x  ${fn:length(board.attachList)}
								</div>							
							</c:if> 					
						</td>
						</tr>					
					</c:forEach>
				</table>
			</div>
			<div class="card-footer">
				<!-- <nav aria-label="board list Navigation"> -->
					<!-- <ul class="pagination justify-content-center m-0"> -->
						<%@ include file="/WEB-INF/views/pagination/pagination.jsp" %>
					<!-- </ul> -->
				<!-- </nav> -->
			</div>
		</div>
	</section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
	<form id="jobForm" accept-charset="utf-8">
		  <input type='hidden' name="page" value="${pageMaker.cri.page}" />
		  <input type='hidden' name="perPageNum" 
		  		 value="${pageMaker.cri.perPageNum}"/>
		  <input type='hidden' name="searchType" 
		         value="${pageMaker.cri.searchType }" />
		  <input type='hidden' name="keyword" 
		         value="${pageMaker.cri.keyword }" />
	</form>
	<form role="form">
	  	<input type='hidden' name='bno' value ="${board.bno}">  	
	  	<input type='hidden' name='page' value ="${pageMaker.cri.page}">  	
	  	<input type='hidden' name='perPageNum' value ="${pageMaker.cri.perPageNum}">  	
	  	<input type='hidden' name='searchType' value ="${pageMaker.cri.searchType}">  	
	  	<input type='hidden' name='keyword' value ="${pageMaker.cri.keyword}"> 		  	
   </form>
	
 	<%@ include file="./list_js.jsp" %>
 	<script>
 		$('#searchBtn').on('click',function(e){
 			
 			var jobForm = $('#jobForm');
 			jobForm.find("[name='page']").val(1);
 			jobForm.find("[name='searchType']").val($('select[name="searchType"]').val());
 			jobForm.find("[name='keyword']").val($('input[name="keyword"]').val());
 			
 			/* alert(jobForm.serialize());  */
  			   jobForm.attr("action","list.do").attr("method","get");
 			   jobForm.submit(); 		
 			
 		});
 	
 	</script>
 
 
</body>






  