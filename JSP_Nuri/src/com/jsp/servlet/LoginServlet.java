package com.jsp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDException;
import com.jsp.exception.NullidException;
import com.jsp.exception.NullpasswordException;
import com.jsp.service.MemberServiceImpl;
import com.jsp.utils.ViewResolver;


@WebServlet("/commons/login")
public class LoginServlet extends HttpServlet {
	
	/*public void init(ServletConfig config) throws ServletException {
		System.out.println("init() execute!");
	}

	public void destroy() {
		System.out.println("destroy() execute!");
	}*/

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/WEB-INF/views/commons/loginForm.jsp";
		
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="redirect:/member/list";
		
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		
		HttpSession session = request.getSession();
		
		try {
			MemberServiceImpl.getInstance().login(id, pwd);
			
			MemberVO loginUser=MemberServiceImpl.getInstance().getMember(id);
			if(loginUser.getEnabled()==0) {
				session.setAttribute("loginUser", loginUser);
				request.setAttribute("msg", "비활성화된 유저에요.");
				session.setMaxInactiveInterval(100*1); // 세션 유지시간
			}else {
				session.setAttribute("loginUser", loginUser);
				session.setMaxInactiveInterval(100*1); // 세션 유지시간
			}
		} catch (SQLException e) {
			e.printStackTrace();
			url="error/500_error";
			request.setAttribute("exception", e);
			
		} catch (NotFoundIDException | InvalidPasswordException | NullidException | NullpasswordException e) {
			e.printStackTrace();
			url="commons/loginForm";
			request.setAttribute("msg", e.getMessage());
		} 
				
		ViewResolver.view(request, response, url);
				
	}

}
