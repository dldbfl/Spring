package com.jsp.action.board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.dto.BoardVO;
import com.jsp.dto.BoardVO;
import com.jsp.request.PageMaker;
import com.jsp.request.SearchCriteria;
import com.jsp.service.BoardService;
import com.jsp.service.BoardServiceImpl;
import com.jsp.service.BoardService;
import com.jsp.utils.CreatePageMaker;
import com.jsp.utils.MakeFileName;

public class BoardModifyFormAction implements Action {

	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
		
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "board/modify";
		int bno = Integer.parseInt(request.getParameter("bno"));

		try {
			
			BoardVO board = boardService.getBoard(bno);
			
			List<AttachVO> renamedAttachList = MakeFileName.parseFileNameFromAttaches(board.getAttachList(), "\\$\\$");
			board.setAttachList(renamedAttachList);
			
			request.setAttribute("board", board);
			
			PageMaker pageMaker = CreatePageMaker.make(request);			
			
			request.setAttribute("pageMaker", pageMaker);
			
		}catch(Exception e) {
			e.printStackTrace();
			url = "error/500_error";
		}				
		return url;
	}

}









