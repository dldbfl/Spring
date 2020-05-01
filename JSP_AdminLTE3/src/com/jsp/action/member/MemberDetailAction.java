package com.jsp.action.member;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dispatcher.ViewResolver;
import com.jsp.dto.BoardVO;
import com.jsp.dto.MemberVO;
import com.jsp.request.SearchCriteria;
import com.jsp.service.BoardService;
import com.jsp.service.MemberService;
import com.jsp.service.MemberServiceImpl;

public class MemberDetailAction implements Action {
	
	private MemberService memberService;// = new BoardServiceImpl.getInstance();
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//화면 결정
				String url = "member/detail";
				
				//파라메터 처리
				String id=request.getParameter("id");
				
				
				MemberVO member =null;
				//서비스 요청 - 결과
				try {
					member = memberService.getMember(id);
					
				} catch (SQLException e) {
					url="error/500_error";
					request.setAttribute("exception", e);
					e.printStackTrace();
				}	
				//결과에 따른 화면 분할
				request.setAttribute("member", member);
				
		return url;
	}

}









