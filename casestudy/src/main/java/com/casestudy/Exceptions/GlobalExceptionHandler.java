package com.casestudy.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.casestudy.helper.ErrorMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<ErrorMessage> courseException(CourseNotFoundException ex){
			
		return new ResponseEntity<>(new ErrorMessage(ex.getMessage(), HttpStatus.NOT_FOUND.value(),"not found"),HttpStatus.NOT_FOUND);
	}
	
}
