package com.jsp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsp.request.PageMaker;
import com.jsp.request.SearchCriteria;
import com.jsp.dao.AttachDAO;
import com.jsp.dao.BoardDAO;
import com.jsp.dao.ReplyDAO;
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
	
	private ReplyDAO replyDAO;
	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}
	
	@Override
	public Map<String, Object> getList(SearchCriteria cri) throws SQLException {
		
		List<BoardVO> boardList = boardDAO.selectBoardCriteria(cri);
						
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(boardDAO.selectBoardCriteriaTotalCount(cri));
		
		List<AttachVO> attachList = new ArrayList<>();
		
		
		for(int i=0; i<boardList.size(); i++) {
			
			//근데 밑에껄 이해하면 위에꺼가 왜 작동하는지 모르겠어.
			int bno = boardList.get(i).getBno();
			System.out.println("bno : " + bno);
			attachList = attachDAO.selectAttachesByBno(bno);
			System.out.println("attachList : " + attachList);
			boardList.get(i).setAttachList(attachList);
			
			//얘는 가동하지 않아. 하지만 안되는 이유는 대충알거같아.
			/*attachList.clear();
			int bno = boardList.get(i).getBno();
			System.out.println("bno : " + bno);
			attachList.addAll(attachDAO.selectAttachesByBno(boardList.get(i).getBno()));
			System.out.println("attachList : " + attachList);
			boardList.get(i).setAttachList(attachList);*/
			
			
			/*System.out.println("boardList : "+boardList.get(i));
			System.out.println("boardList.get("+i+").getAttachList(): "+boardList.get(i).getAttachList());*/
			
			}
	
		//댓글 갯수넣기
		for(BoardVO board : boardList) {
			int replycnt=replyDAO.countReply(board.getBno());
			board.setReplycnt(replycnt);
		}		
		
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("boardList", boardList);
		dataMap.put("pageMaker", pageMaker);
		
		/*for(int i=0; i<boardList.size(); i++) {
			
			System.out.println("넣고나서 boardList : "+boardList.get(i));
			System.out.println("넣고나서 boardList.get("+i+").getAttachList(): "+boardList.get(i).getAttachList());

		}*/
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
