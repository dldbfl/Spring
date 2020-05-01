package com.jsp.service;

import java.sql.SQLException;
import java.util.Map;

import com.jsp.request.SearchCriteria;
import com.jsp.dto.BoardVO;

public interface BoardService {
		// 리스트조회
		Map<String, Object> getList(SearchCriteria cri) throws SQLException;

		// 글조회
		BoardVO getBoard(int bno) throws SQLException;

		// 글작성
		void regist(BoardVO board) throws SQLException;

		// 글수정
		void modify(BoardVO board) throws SQLException;

		// 글삭제
		void remove(int bno) throws SQLException;

		// 글읽기(조회수증가)
		BoardVO read(int bno) throws SQLException;
}
