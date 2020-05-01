package com.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/login.html")
public class LoginServlet extends HttpServlet {
	    
    
	@Override // 에노테이션도 선언자다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			//  출력지가 SYSTEM에서 response로 바뀜
			
			request.getRequestDispatcher("loginForm.jsp").forward(request, response);
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		//MemberVO member = memberService.getMember(id);
		
		request.getRequestDispatcher("login_success.jsp").forward(request, response);
		
		
	}

}
