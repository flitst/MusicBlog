package com.explorer.musicblog.exception;
/**
 * zhangzhong
 * Nov 23, 2019 3:34:58 PM
 */
public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public CustomException() {}

	public CustomException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
}
