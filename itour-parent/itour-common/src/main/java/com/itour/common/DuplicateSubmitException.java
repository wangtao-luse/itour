package com.itour.common;

public class DuplicateSubmitException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public DuplicateSubmitException() {}
	public DuplicateSubmitException(String message) {
		super(message);
	}
	public DuplicateSubmitException(String message,Throwable cause) {
		super(message,cause);
	}

}
