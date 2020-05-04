<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="x-ua-compatible" content="ie=edge">

  <title><decorator:title default="" /></title>

  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/dist/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
	
  
  <!-- jQuery -->
  <script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
	 
  <!-- swal fire -->	
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
  <script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/toastr/toastr.min.js"></script>
  <!-- 대전시청 css -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/cityrayout.css">	
<decorator:head/>
<style>

	
</style>
</head>
<body class="sidebar-collapse layout-top-nav">

  <!-- Navbar -->
  <nav class="main-header navbar navbar-expand-md navbar-light navbar-white">	
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#"><i class="fas fa-bars"></i></a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="<%=request.getContextPath() %>/board/list.do" class="nav-link">대전시 - 자유게시판</a>
      </li>
      <!-- <li class="nav-item dropdown show">
      	<a id="dropdownSubMenu1" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" class="nav-link dropdown-toggle">Dropdown</a>
      		드롭다운 내용
      		<ul aria-labelledby="dropdownSubMenu1" class="dropdown-menu border-0 shadow show">
              <li><a href="#" class="dropdown-item">Some action </a></li>
              <li><a href="#" class="dropdown-item">Some other action</a></li>

              <li class="dropdown-divider"></li>

              Level two dropdown
              <li class="dropdown-submenu dropdown-hover">
                <a id="dropdownSubMenu2" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-item dropdown-toggle">Hover for action</a>
                <ul aria-labelledby="dropdownSubMenu2" class="dropdown-menu border-0 shadow">
                  <li>
                    <a tabindex="-1" href="#" class="dropdown-item">level 2</a>
                  </li>

                  Level three dropdown
                  <li class="dropdown-submenu">
                    <a id="dropdownSubMenu3" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-item dropdown-toggle">level 2</a>
                    <ul aria-labelledby="dropdownSubMenu3" class="dropdown-menu border-0 shadow">
                      <li><a href="#" class="dropdown-item">3rd level</a></li>
                      <li><a href="#" class="dropdown-item">3rd level</a></li>
                    </ul>
                  </li>
                  End Level three

                  <li><a href="#" class="dropdown-item">level 2</a></li>
                  <li><a href="#" class="dropdown-item">level 2</a></li>
                </ul>
              </li>
              End Level two
            </ul> 
                 
      </li> -->

    </ul>
    <ul class ="order-1 order-md-3 navbar-nav navbar-no-expand ml-auto">
		<li>
			<button class="btn btn-block btn-outline-secondary btn-flat " type="button" 
          		onclick="location.href='<%=request.getContextPath() %>/commons/logout.do';" >Logout</button>
		</li>
	</ul>
   
  </nav>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    
      <img src="<%=request.getContextPath()%>/resources/images/topleft.png" 
           style="opacity: .;width:250px; height:auto;">
      

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
       
        <div class="info">
          <div class="row">
          
          </div>
		          
        </div>
      </div>

      <!-- Sidebar Menu -->
      <nav class="mt-2">
      
      
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview"
        	data-accordion="false">
     
	        <li class="nav-item" >
                <a href="<%=request.getContextPath() %>/board/list.do" class="nav-link">
                  <i class="fas fa-comments nav-icon"></i>
                  <p>자유게시판</p>
                </a>
	        </li>

        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>
