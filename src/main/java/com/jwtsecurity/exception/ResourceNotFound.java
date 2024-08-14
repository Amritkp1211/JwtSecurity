package com.jwtsecurity.exception;

public class ResourceNotFound extends RuntimeException {

	private String message;

	public ResourceNotFound(String message) {
		super();
		this.message = message;
	}

}
