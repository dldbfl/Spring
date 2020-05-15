package com.groupware.exception;

public class InvalidPasswordException extends Exception {
	public InvalidPasswordException() {
		super("패스워드가일치하지않어");
	}
}
