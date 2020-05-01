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
import com.jsp.service.MemberServiceImpl;
import com.jsp.utils.ViewResolver;

@WebServlet("/member/checkId")
public class CheckIdServlet extends HttpServlet {
	 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String result = null;
    	
    	String id = request.getParameter("id");
    	
    	if(id=="") {
    		result= "SPACE";
    		response.getWriter().print(result);
    	
    	} else {
    		
    		MemberVO member =null;
    		
    		try {
    			member = MemberServiceImpl.getInstance().getMember(id);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		
    		if(member!=null) {
    			result= "SUCCESS";
    		}
    		
    		response.getWriter().print(result);
    	}
	
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
