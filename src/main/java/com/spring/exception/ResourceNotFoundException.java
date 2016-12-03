package com.spring.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5428649613320127527L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
