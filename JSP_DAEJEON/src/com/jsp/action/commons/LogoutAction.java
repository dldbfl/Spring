package com.jsp.action.commons;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.dispatcher.ViewResolver;
import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDException;
import com.jsp.exception.NullidException;
import com.jsp.exception.NullpasswordException;
import com.jsp.service.MemberService;
import com.jsp.service.MemberServiceImpl;

public class LogoutAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "redirect:/commons/loginForm.do";
		
		HttpSession session = request.getSession();
		session.invalidate();
				
		return url;
	}

}
