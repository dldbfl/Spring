package com.spring.exception;

public class EmptyMultipartFileException extends Exception {
	
	public EmptyMultipartFileException() {
		super("첨부파일이 비어있소!");
	}
}
