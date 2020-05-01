package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsp.request.PageMaker;
import com.jsp.request.SearchCriteria;
import com.jsp.dao.AttachDAO;
import com.jsp.dao.BoardDAO;
import com.jsp.dto.AttachVO;
import com.jsp.dto.BoardVO;

public class BoardServiceImpl implements BoardService {
	
		
	private BoardDAO boardDAO;
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	
	private AttachDAO attachDAO;
	public void setAttachDAO(AttachDAO attachDAO) {
		this.attachDAO=attachDAO;
	}
	
	@Override
	public Map<String, Object> getList(SearchCriteria cri) throws SQLException {
		
		List<BoardVO> boardList = boardDAO.selectBoardCriteria(cri);
						
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(boardDAO.selectBoardCriteriaTotalCount(cri));
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("boardList", boardList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}
	@Override
	public BoardVO getBoard(int bno) throws SQLException {
		BoardVO board = boardDAO.selectBoardByBno(bno);
		List<AttachVO> attachList=attachDAO.selectAttachesByBno(bno);
		board.setAttachList(attachList);
		return board;
	}
	@Override
	public void regist(BoardVO board) throws SQLException {
		int bno = boardDAO.getSeqNextValue();
		board.setBno(bno);
		boardDAO.insertBoard(board);
		for(AttachVO attach:board.getAttachList()) {
			attach.setBno(bno);
			attach.setAttacher(board.getWriter());
			attachDAO.insertAttach(attach);
		}
	}
	@Override
	public void modify(BoardVO board) throws SQLException {
		boardDAO.updateBoard(board);		
		System.out.println(board);
		//attachDAO.deleteAllAttach(board.getBno());
		for(AttachVO attach:board.getAttachList()) {
			attach.setBno(board.getBno());
			attach.setAttacher(board.getWriter());
			attachDAO.insertAttach(attach);
		}
	}
	@Override
	public void remove(int bno) throws SQLException {
		boardDAO.deleteBoard(bno);		
	}
	@Override
	public BoardVO read(int bno) throws SQLException {
		BoardVO board = boardDAO.selectBoardByBno(bno);
		List<AttachVO> attachList=attachDAO.selectAttachesByBno(bno);
		board.setAttachList(attachList);
		boardDAO.increaseViewCnt(bno);
		
		
		return board;
	}
	
	
}
