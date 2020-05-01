package com.jsp.action.member;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.dispatcher.ViewResolver;
import com.jsp.dto.BoardVO;
import com.jsp.dto.MemberVO;
import com.jsp.request.MemberRegistRequest;
import com.jsp.request.SearchCriteria;
import com.jsp.service.BoardService;
import com.jsp.service.MemberService;
import com.jsp.service.MemberServiceImpl;
import com.jsp.utils.GetUploadPath;

public class MemberModifyFormAction implements Action {
	
	private MemberService memberService;// = new BoardServiceImpl.getInstance();
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url="member/modify";		
		String id=request.getParameter("id");		
		MemberVO member=null;
		try {
			member=memberService.getMember(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("member", member);
						
		return url;
	}

}









