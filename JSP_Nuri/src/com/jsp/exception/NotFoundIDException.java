package com.jsp.exception;

public class NotFoundIDException extends Exception {
	
	public NotFoundIDException() {
		super("등록되지 않은 아이디에요.");
	}

}
