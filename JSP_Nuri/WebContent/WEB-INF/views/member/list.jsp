<%@page import="com.jsp.dto.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <%@ include file="/WEB-INF/views/include/header.jsp" %> --%>
<head>
	<title>픽투르 시스템즈</title>
		<style>
		.user-panel .info {
	    white-space: normal;
		}
		#logoutbtn{
		width:70%;
		}
		.card-header>.card-tools .input-group{
	    margin-bottom: 0rem;
	    margin-top: 0rem;
	    margin-left: 0px;
		}
		#searchType{
		margin-left: 37px;
		}
		.btn-primary {
		margin-bottom: 7px;
		}
		.btn-primary {
		margin-right: 11px;
		}
		
		@import "https://fonts.googleapis.com/css?family=Montserrat:300,400,700";
		.rwd-table {
		  margin: 1em 0;
		  min-width: 300px;
		  
		}
		.rwd-table tr {
		  border-top: 1px solid #ddd;
		  border-bottom: 1px solid #ddd; 
		}
		.rwd-table th {
		  display: none;
		}
		.rwd-table td {
		  display: block;
		}
		.rwd-table td:first-child {
		  padding-top: .5em;
		}
		.rwd-table td:last-child {
		  padding-bottom: .5em;
		}
		.rwd-table td:before {
		  content: attr(data-th) ": ";
		  font-weight: bold;
		  width: 6.5em;
		  display: inline-block;
		}
		@media (min-width: 480px) {
		  .rwd-table td:before {
		    display: none;
		  }
		}
		.rwd-table th,
		.rwd-table td {
		  text-align: left;
		}
		@media (min-width: 480px) {
		  .rwd-table th,
		  .rwd-table td {
		    display: table-cell;
		    padding: .25em .5em;
		  }
		  .rwd-table th:first-child,
		  .rwd-table td:first-child {
		    padding-left: 0;
		  }
		  .rwd-table th:last-child,
		  .rwd-table td:last-child {
		    padding-right: 0;
		  }
		}
		
		body {
		  /* padding: 0 2em; */
		  font-family: sans-serif;
		  color: #444;
		  background: #eee;
		}
		
		h1 {
		  font-weight: normal;
		  letter-spacing: -1px;
		  color: #34495E;
		}
		
		.rwd-table {
		  /* background: mintcream; */
		  /* color: #fff; */
		  /* border-radius: .4em; */
		  overflow: hidden;
		}
		.rwd-table tr {
		  border-color: #46637f;
		}
		.rwd-table th,
		.rwd-table td {
		  margin: .5em 1em;
		}
		@media (min-width: 480px) {
		  .rwd-table th,
		  .rwd-table td {
		    padding: 1em !important;
		  }
		}
		.rwd-table th,
		.rwd-table td:before {
		  color: black;
		}
		
		
		.card-body{
			padding-top: 0em;
			padding-left:1.25em;
			padding-right:1.25em;
			padding-bottom:0em;
		}
		</style>
</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		  <section class="content-header">
		  	<div class="container-fluid">
		  		<div class="row md-2">
		  			<div class="col-sm-6">
		  				<h1>회원리스트</h1>
		  			</div>
		  			<div class="col-sm-6">
		  				<ol class="breadcrumb float-sm-right">
					        <li class="breadcrumb-item">
					        	<a href="lis">
						        	<i class="fa fa-dashboard"></i> 회원관리
						        </a>
					        </li>
					        <li class="breadcrumb-item active">
					        	리스트
					        </li>		        
    	  				</ol>
  					</div>
  				</div>
  			</div>
  		</section>
       <!-- Main content -->
    	<section class="content">
    	  <div class="card">    
    	  	<div class="card-header with-border">
    	  		<c:if test="${loginUser.authority eq 'ROLE_ADMIN' }" >
    	  			<button type="button" class="btn btn-primary" 
    	  			onclick="OpenWindow('regist','회원등록',800,600);" >회원등록</button>
    	  		</c:if>
    	  		
    	  		<div id="keyword" class="card-tools" style="width:350px;">
				  <div class="input-group row">		
				  <!-- search bar -->	  										
					<select class="form-control col-md-4" name="searchType" id="searchType">
						<option value=""  ${pageMaker.cri.searchType eq '' ? 'selected':''}>검색구분</option>
						<option value="i"  ${pageMaker.cri.searchType eq 'i' ? 'selected':''}>아이디</option>
						<option value="p"  ${pageMaker.cri.searchType eq 'p' ? 'selected':''}>전화번호</option>
						<option value="e"  ${pageMaker.cri.searchType eq 'e' ? 'selected':''}>이메일</option>
					</select>			
					<input  class="form-control" type="text" name="keyword" 
					placeholder="검색어를 입력하세요." value="${param.keyword }"/>
					<span class="input-group-append">
						<button class="btn btn-primary" type="button" 
						id="searchBtn" data-card-widget="search" onclick="searchList_go(1);">
							<i class="fa fa-fw fa-search"></i>
						</button>
					</span>
					<!-- end : search bar -->
				  </div>
				 </div>    	  		
    	  	</div>	  
    		<div class="card-body" style="text-align:center;">
    		  <div class="row">
	             <div class="col-sm-12">
	            	<table class="rwd-table"> 
	             		<tr>	
	             			<th>ID</th>
	             			<th>NAME</th>
	             			<th>PASSWORD</th>
	             			<th>E-MAIL</th>
	             			<th>PHONE</th>
	             		</tr>
             			
             			<c:if test="${not empty memberList }">
             			
             				<c:forEach var="member" items="${memberList }">
             					<tr>	
			             			<td data-th="ID"><a href="javascript:OpenWindow('detail?id=${member.id}','회원상세보기','800','600');">${member.id }</a></td>
			             			<td data-th="NAME">${member.name}</td>
			             			<td data-th="PASSWORD">${member.pwd}</td>
			             			<td data-th="E-MAIL">${member.email}</td>
			             			<td data-th="PHONE">${member.phone}</td>
			             		</tr>
		             		</c:forEach>
		              	</c:if>
             			<c:if test="${empty memberList }">
             			
             			<tr>	
	             			<td colspan="5">회원정보를 볼 수 없습니다.</td>
	             		</tr>	
             			 </c:if>
				 	</table>
				 	
            	</div>
           	</div>            
       	  </div>   
		  <div class="card-footer">
		   <%@ include file= "/WEB-INF/views/pagination/pagination.jsp" %>
		  </div> <!-- card-footer -->
        </div> <!-- card  -->
      </section>	
    </div>

<%-- <%@ include file="/WEB-INF/views/include/footer.jsp" %> --%>

</body>