package com.jsp.action.member;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.action.Action;
import com.jsp.dto.BoardVO;
import com.jsp.dto.MemberVO;
import com.jsp.request.SearchCriteria;
import com.jsp.utils.GetUploadPath;
import com.jsp.utils.MakeFileName;
import com.jsp.utils.MakeLogForException;

public class MemberCheckPasswoodAction implements Action {
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String result = null;
    	
    	String pwd = request.getParameter("pwd");
    	
    	HttpSession session = request.getSession();
    	MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
    	if(loginUser.getPwd().equals(pwd)) {
    		result= "SUCCESS";
    	}
				
		response.getWriter().print(result);
	
				
		return null;
	}
	

}









