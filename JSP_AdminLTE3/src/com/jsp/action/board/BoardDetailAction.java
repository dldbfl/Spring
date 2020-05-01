package com.jsp.action.board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.BoardVO;
import com.jsp.request.SearchCriteria;
import com.jsp.service.BoardService;
import com.jsp.service.BoardServiceImpl;

public class BoardDetailAction implements Action {
	
	private BoardService boardService;// = BoardServiceImpl.getInstance();
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "board/detailBoard";
		String state=request.getParameter("state");
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		BoardVO board =new BoardVO();
		
		try {
			if(state.equals("modify")) {
				 board = boardService.getBoardForModify(bno);
			}else if(state.equals("list")){
				 board = boardService.getBoard(bno);
			}
			 
			 request.setAttribute("board", board);
						
		} catch (SQLException e) {
			e.printStackTrace();
			url="error/500_error";
		} 
		
		return url;
	}

}









