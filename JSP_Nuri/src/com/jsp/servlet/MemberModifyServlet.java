package com.jsp.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.MemberVO;
import com.jsp.request.MemberModifyRequest;
import com.jsp.request.MemberRegistRequest;
import com.jsp.service.MemberServiceImpl;
import com.jsp.utils.GetUploadPath;
import com.jsp.utils.ViewResolver;

@WebServlet("/member/modify")
public class MemberModifyServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/modify";
			   
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
		
		System.out.println(member);
		//화면요청
		ViewResolver.view(request, response, url);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		  String   id         = request.getParameter("id");
		  String   pwd        = request.getParameter("pwd"); 
		  String   email      = request.getParameter("email"); 
		  String   picture    = request.getParameter("picture"); 
		  String[] phone      = request.getParameterValues("phone"); 
		  String   authority  = request.getParameter("authority");
		  String   name       = request.getParameter("name");
		   
		  String url="member/modify_success";
	      //String url="redirect:/member/detail?id="+id; // 자 바로 리다이렉트 해버리겠소!
	      
	      MemberRegistRequest memberReq = new MemberRegistRequest(id,pwd,authority,email,phone,picture,name);
	      
	      MemberVO member = memberReq.toMemberVO();

	      	try {
				MemberServiceImpl.getInstance().modify(member);
				
				HttpSession session = request.getSession();
				MemberVO loginUser=(MemberVO)session.getAttribute("loginUser");
				if(member.getId().equals(loginUser.getId())) {
					session.setAttribute("loginUser", member);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				url="member/modify_fail";
				String oldFileName = member.getPicture();
				String uploadPath=GetUploadPath.getUploadPath("member.picture.upload");
				File oldFile=new File(uploadPath+File.separator+oldFileName);
				if(oldFile.exists()) {
					oldFile.delete();
				}
			}
	      	
	    	request.setAttribute("id", id);
			
	      	ViewResolver.view(request, response, url);
	      
	      
	}

}
