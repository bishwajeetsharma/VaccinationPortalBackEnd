package com.example.demo.Exception;

public class ResourceExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8795311659021549183L;

	public ResourceExistsException(String message) {
		super(message);
	}
}
