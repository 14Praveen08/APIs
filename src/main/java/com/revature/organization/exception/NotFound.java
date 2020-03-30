package com.revature.organization.exception;

public class NotFound extends Exception {

	private int statusCode;
	private String message;

	public NotFound(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

}
