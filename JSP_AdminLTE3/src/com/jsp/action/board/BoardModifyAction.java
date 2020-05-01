package com.jsp.action.board;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.dto.BoardVO;
import com.jsp.dto.MemberVO;
import com.jsp.request.BoardRequest;
import com.jsp.request.MemberRegistRequest;
import com.jsp.request.ModifyBoardRequest;
import com.jsp.request.SearchCriteria;
import com.jsp.service.BoardService;
import com.jsp.service.BoardServiceImpl;
import com.jsp.service.MemberServiceImpl;
import com.jsp.utils.GetUploadPath;

public class BoardModifyAction implements Action {
	
	private BoardService boardService;// = BoardServiceImpl.getInstance();
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String url="board/modifyBoard_success";
		
		// 파라메터 저장
		int 	 bno		   = Integer.parseInt(request.getParameter("bno"));
		String   title         = request.getParameter("title");
		String   writer        = request.getParameter("writer"); 
		String   content       = request.getParameter("content");
		
		String page = request.getParameter("page");
		String perPageNum = request.getParameter("perPageNum");
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");
		
		// 화면결정
		String url = "redirect:detail.do?bno="+bno+"&state=modify";	
		
		// url 파라메터를 String 으로 만들경우 한글깨짐방지
		url += "&page=" + page + "&perPageNum=" + perPageNum + "&searchType=" 
				  + searchType + "&keyword="+ URLEncoder.encode(keyword, "utf-8");
		
		BoardVO board = new ModifyBoardRequest(bno, title, content, writer).toBoardVO();

		
		// 서비스를 의뢰 (결과 할당)
				try {
					boardService.modify(board);
				} catch (SQLException e) {
					e.printStackTrace();
					url="error/500.jsp";
				}
		// request.setAttribut() 없음.

		System.out.println(board);
		
		
		
		return url;
	}

}









