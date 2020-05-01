package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import com.jsp.dto.BoardVO;
import com.jsp.request.SearchCriteria;

public interface BoardDAO {
	//회원리스트
	List<BoardVO> selectBoardCriteria(SearchCriteria cri) throws SQLException;
	
	//전체 회원리스트 개수
	int selectBoardCriteriaTotalCount(SearchCriteria cri) throws SQLException;
	
	//공지사항 번호 조회
	BoardVO selectBoardByBno(int bno) throws SQLException;

	//회원정보 추가
	void insertBoard(BoardVO board) throws SQLException;

	//회원정보 수정
	void updateBoard(BoardVO board) throws SQLException;

	//회원정보 삭제
	void deleteBoard(int bno) throws SQLException;

	// viewcnt 증가
	void increaseViewCnt(int bno) throws SQLException;

	// board_seq.nextval 가져오기
	int selectBoardSeqNext() throws SQLException;

}
