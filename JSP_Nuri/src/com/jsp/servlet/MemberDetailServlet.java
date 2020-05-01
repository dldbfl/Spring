package com.jsp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDException;
import com.jsp.service.MemberServiceImpl;
import com.jsp.utils.ViewResolver;

@WebServlet("/member/detail")
public class MemberDetailServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//화면 결정
		String url = "member/detail";
		
		//파라메터 처리
		String id=request.getParameter("id");
		
		
		MemberVO member =null;
		//서비스 요청 - 결과
		try {
			member = MemberServiceImpl.getInstance().getMember(id);
			
		} catch (SQLException e) {
			url="error/500_error";
			request.setAttribute("exception", e);
			e.printStackTrace();
		}	
		//결과에 따른 화면 분할
		request.setAttribute("member", member);
		
		
		//화면요청
		ViewResolver.view(request, response, url);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
