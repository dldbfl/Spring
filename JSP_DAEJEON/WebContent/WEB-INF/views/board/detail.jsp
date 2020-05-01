<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
	<title>상세보기</title>
	<!-- Ionicons -->
  	<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
</head>
<body>
  <!-- Content Wrapper. Contains page content -->
  <div >
  	  <jsp:include page="content_header.jsp">
    	<jsp:param value="자유게시판" name="subject"/>
		<jsp:param value="상세보기" name="item"/>
		<jsp:param value="list.do" name="url"/>    	
    </jsp:include>

    <!-- Main content -->
    <section class="content container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-outline card-primary">
					<div class="card-header">
						<h3 class="card-title">상세보기</h3>
					</div>
					<div class="card-body">
						<div class="form-group col-sm-12">
							<label for="title">제 목</label>
							<input type="text" class="form-control" id="title" 
								value="${board.title }" readonly />							
						</div>	
						<div class="col-sm-12 row">
							<div class="form-group col-sm-4" >
								<label for="writer">작성자</label>
								<input type="text" class="form-control" id="writer" 
									 value="${board.writer }" readonly />
							</div>		
							
							<div class="form-group col-sm-4" >
								<label for="regDate">작성일</label>
								<input type="text" class="form-control" id="regDate" 
									value="<fmt:formatDate value="${board.regDate }" pattern="yyyy-MM-dd" />" readonly />
							</div>	
							<div class="form-group col-sm-4" >
								<label for="viewcnt">조회수</label>
								<input type="text" class="form-control" id="viewcnt" value="${board.viewcnt }"
									 readonly />
							</div>	
						</div>	
						<div class="form-group col-sm-12">
							<label for="content">내 용</label>
							<div id="content">${board.content }</div>	
						</div>
						<div class="form-group col-sm-12">
							<div class="card card-outline card-success">
								<div class="card-header">
									<h3>첨부파일</h3>
								</div>			
								<div class="card-footer">
									<div class="row">
										<c:forEach items="${board.attachList }" var="attach">
											<div class="col-md-4 col-sm-4 col-xs-12"  style="cursor:pointer;" onclick="location.href='<%=request.getContextPath()%>/attach/getFile.do?bno=${board.bno }&ano=${attach.ano }';">
												<div class="info-box">	
												 	<span class="info-box-icon bg-yellow">
														<i class="fa fa-copy"></i>
													</span>
													<div class="info-box-content">
														<span class ="info-box-text">
															<fmt:formatDate value="${attach.regDate }" pattern="yyyy-MM-dd" />	
														</span>
														<span class ="info-box-number">${attach.fileName }</span>
													</div>
												</div>
											 </div>											 
										</c:forEach>
									</div>
								</div>									
							</div>
						</div>
												
					</div>
					<div class="card-footer">
						<c:if test="${loginUser.id eq board.writer }">
							<button type="button" id="modifyBtn" class="btn btn-warning">Modify</button>						
					    	<button type="button" id="removeBtn" class="btn btn-danger">REMOVE</button>
					   </c:if>
					    <button type="button" id="listBtn" class="btn btn-primary">GO LIST </button>
					</div>									
				</div><!-- end card -->				
			</div><!-- end col-md-12 -->
		</div><!-- end row  -->		
		
		
		<!-- reply component start --> 
		<div class="row">
			<div class="col-md-12">
				<div class="card card-info">					
					<div class="card-body">
						<!-- The time line -->
						<div class="timeline">
							<!-- timeline time label -->
							<div class="time-label" id="repliesDiv">
								<span class="bg-green">Replies List </span>							
							</div>
							
						</div>
						<div class='text-center'>
							<ul id="pagination" class="pagination justify-content-center m-0">
			
							</ul>
						</div>
					</div>
					<div class="card-footer">
						<label for="newReplyWriter">Writer</label>
						<input class="form-control" type="text" placeholder="USER ID" 
							   type ="hidden"
							   id="newReplyWriter" readonly value="${loginUser.id }"> 
						<label for="newReplyText">Reply Text</label>
						<input class="form-control" type="text"	placeholder="REPLY TEXT" 
						       id="newReplyText">
						<br/>
						<button type="button" class="btn btn-primary" 
								id="replyAddBtn">ADD REPLY</button>
					</div>				
				</div>			
				
			</div><!-- end col-md-12 -->
		</div><!-- end row -->
		
		
		
		
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
   <form role="form">
  	<input type='hidden' name='bno' value ="${board.bno}">  	
  	<input type='hidden' name='page' value ="${pageMaker.cri.page}">  	
  	<input type='hidden' name='perPageNum' value ="${pageMaker.cri.perPageNum}">  	
  	<input type='hidden' name='searchType' value ="${pageMaker.cri.searchType}">  	
  	<input type='hidden' name='keyword' value ="${pageMaker.cri.keyword}">  	
  	
   </form>
   
   <!-- Modal -->
<div id="modifyModal" class="modal modal-default fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" style="display:none;"></h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>        
      </div>
      <div class="modal-body" data-rno>
        <p><input type="text" id="replytext" class="form-control"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-info" id="replyModBtn">Modify</button>
        <button type="button" class="btn btn-danger" id="replyDelBtn">DELETE</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
  
   <%@ include file="./detail_js.jsp" %>
   <%@ include file="./reply_js.jsp" %>	
</body>






  