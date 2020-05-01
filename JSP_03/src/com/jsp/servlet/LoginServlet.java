package com.jsp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.service.LoginService;
import com.jsp.service.LoginServiceImpl;
import com.jsp.vo.MemberVO;

	


//@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private LoginService loginService =LoginServiceImpl.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/common/loginForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "/WEB-INF/views/common/login_success.jsp";
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		//로그인 처리부.. MemberVO member = mService.getMember(id);
		
		MemberVO mv = new MemberVO();
		mv.setId(id);
		mv.setPwd(pwd);
		
		System.out.println(mv);
		MemberVO loginMember = loginService.loginMember(mv);
		
		//if(!(id.equals("mimi")&& pwd.equals("mimi"))) {
		if(loginMember == null) {
			request.setAttribute("id", id);
			url = "/WEB-INF/views/common/loginForm.jsp";
		} else {


			
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginMember);
			
		}
		//response.sendRedirect("/main");
		request.getRequestDispatcher(url).forward(request, response);
	}

}
