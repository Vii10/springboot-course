package com.vii10.course.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vii10.course.services.exceptions.DatabaseException;
import com.vii10.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

//Intercepta os erros para serem tratados
@ControllerAdvice
public class ResourceExceptionHandler {
	
	//Anotação para interceptar qualquer exception da ResourceNotFoundException
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		//Retornando resposta com código personalizado
		return ResponseEntity.status(status).body(err);
	}
	
	//Anotação para interceptar qualquer exception da Database Exception
		@ExceptionHandler(DatabaseException.class)
		public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
			String error = "Data base error";
			HttpStatus status = HttpStatus.BAD_REQUEST;
			StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
			//Retornando resposta com código personalizado
			return ResponseEntity.status(status).body(err);
		}
}
