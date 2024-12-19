package com.casestudy.helper;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorMessage {
	
	private String message;
	private int status;
	private String error;
	
}
