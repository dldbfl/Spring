<%@page import="java.net.URLEncoder"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<%
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String age = request.getParameter("age");
	Date birth = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birth"));
	String[] hobby = request.getParameterValues("hobby");		
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	이름 : <%=name %><br/>
	이름 : <%=age %><br/>
	이름 : <%=new SimpleDateFormat("yyyy.MM.dd").format(birth) %><br/>
	취미 : 
	<%
		for(String hobb : hobby){
			out.print(hobb+"  ");
		}
	
	
	%><br/>
	
	<button type ="button" onclick="regist_go();">등록하기</button>
	<form name="frm" action="regist.jsp" method="get">
		<input type = "hidden" name = "name" value="<%=name %>"/>
		<input type = "hidden" name = "age" value="<%=age %>"/>
	</form>
	
	
	
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script>
		function regist_go(){
			alert('click');
			$('form[name="frm"]').submit();
		}
	</script>
	
	<%-- <script type="text/javascript">
	
		var answer = confirm("등록하시겠습니까?");
		if(answer){
			location.href="regist.jsp?name=<%=name%>&age=<%=age%>";
		}else{
			location.href="http://www.ddit.or.kr";
		}
	</script> --%>
	
	<%-- <%
	
		response.setCharacterEncoding("utf-8");
		//response.setContentType("text/html;charset=utf-8");
		if(Integer.parseInt(age)>=20){
			response.sendRedirect("regist.jsp?name="+
			URLEncoder.encode(name, "utf-8")
			+"&age"+age);
		}else{
			response.sendRedirect("http://www.ddit.or.kr");
		}
	%> --%>
	
	

</body>
</html>