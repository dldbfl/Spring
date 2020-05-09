package com.spring.exception;

public class OverflowFileSizeException extends Exception {

	public OverflowFileSizeException() {
		super("파일용량이 초과도있습니다.");
	}
}
