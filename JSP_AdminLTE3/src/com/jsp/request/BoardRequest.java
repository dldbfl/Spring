package com.jsp.request;

import com.jsp.dto.BoardVO;
import com.jsp.dto.MemberVO;

public class BoardRequest {
	private String  title       ;
	private String  writer        ;
	private String  content      ;
	private int bno;
	
	public BoardRequest() {};
		
	
	
	public BoardRequest(String title, String writer, String content, int bno) {
		super();
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.bno = bno;
	}



	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}



	public int getBno() {
		return bno;
	}



	public void setBno(int bno) {
		this.bno = bno;
	}



	public BoardVO toBoardVO() {
		BoardVO board = new BoardVO();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);		
		board.setBno(bno);
		
		return board;
	}
	
	
}

