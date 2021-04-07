package com.example.demo.Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceExistsException.class)
	public ResponseEntity<Details> resourceExistsExceptionHandler(ResourceExistsException ex, WebRequest request) {
		Details errorDetails = new Details(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Details>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<Details> AuthenticationExceptionHandler(AuthenticationException ex, WebRequest request) {
		Details errordetails = new Details(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Details>(errordetails, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Details> globalExceptionHandler(Exception ex, WebRequest request) {
		Details errorDetails = new Details(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Details>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
