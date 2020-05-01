package com.jsp.action.board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.dto.BoardVO;
import com.jsp.request.PageMaker;
import com.jsp.request.SearchCriteria;
import com.jsp.service.BoardService;
import com.jsp.utils.CreatePageMaker;
import com.jsp.utils.MakeFileName;

public class BoardDetailAction implements Action {
	
	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url="board/detail";
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		String from = request.getParameter("from");
		
		
		try {
			BoardVO board =null; 
			if(from!=null && from.equals("modify")) {
				board = boardService.getBoard(bno);
			}else {
				board = boardService.read(bno);
			}
			
			List<AttachVO> renamedAttachList=
					MakeFileName.parseFileNameFromAttaches(board.getAttachList(), "\\$\\$");
			board.setAttachList(renamedAttachList);
			
			request.setAttribute("board", board);

			PageMaker pageMaker = CreatePageMaker.make(request);			
			
			request.setAttribute("pageMaker", pageMaker);
		} catch (Exception e) {			
			e.printStackTrace();
			url="error/500_error";
		}		
		
		return url;
	}

}







