<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/dist/css/adminlte.min.css">
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
<style>
.success-page {
    -ms-flex-align: center;
    align-items: center;
    background: #e9ecef;
    display: -ms-flexbox;
    display: flex;
    -ms-flex-direction: column;
    flex-direction: column;
    height: 100vh;
    -ms-flex-pack: center;
    justify-content: center;
}
.btn-block {
width: 30%;
}
div.row{
justify-content: center;
}
</style>
</head>
<body class="success-page">

	<div class="col-md-4">
            <!-- Widget: user widget style 1 -->
            <div class="card card-widget widget-user">
              <!-- Add the bg color to the header using any of the bg-* classes -->
              <div class="widget-user-header text-white" style="background: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/photo1.png') center center;">
                <h3 class="widget-user-username text-right">${loginUser.id }</h3>
                <h5 class="widget-user-desc text-right">Programmer</h5>
              </div>
              <div class="widget-user-image">
                <img class="img-circle" src="<%=request.getContextPath() %>/resources/bootstrap/dist/img/user2-160x160.jpg" alt="User Avatar">
              </div>
              <div class="card-footer">
                <div class="row">
                  <div class="col-sm-4 border-right">
                    <div class="description-block">
                      <h5 class="description-header">29</h5>
                      <span class="description-text">Age</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-4 border-right">
                    <div class="description-block">
                      <h5 class="description-header">Play together</h5>
                      <span class="description-text">Like</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-4">
                    <div class="description-block">
                      <h5 class="description-header">Drama</h5>
                      <span class="description-text">Hobby</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
	<button class="btn btn-block btn-outline-secondary" id="logoutBtn" type="button" onclick="logout_go();">Log-out</button>
                </div>
                <!-- /.row -->
              </div>
            </div>
            <!-- /.widget-user -->
          </div>




</body>

<script>
	function logout_go(id){
		
		<%session.invalidate();%>	
		<%-- <%=session.getAttribute("id") %> --%>
		location.href="login";
	}
</script>
</html>