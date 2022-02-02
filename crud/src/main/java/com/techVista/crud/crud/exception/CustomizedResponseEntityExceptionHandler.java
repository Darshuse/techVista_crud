package com.techVista.crud.crud.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse exception = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(EmployeeNotFoundException.class)
	public final ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException ex,
			WebRequest request) throws Exception {
		ExceptionResponse exception = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exception, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DepartmentNotFoundException.class)
	public final ResponseEntity<Object> handleDepartmentNotFoundException(DepartmentNotFoundException ex,
			WebRequest request) throws Exception {
		ExceptionResponse exception = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exception, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DepartmentForbiddenDeleteException.class)
	public final ResponseEntity<Object> handleDepartmentForbiddenDeleteException(DepartmentForbiddenDeleteException ex,
			WebRequest request) throws Exception {
		ExceptionResponse exception = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exception, HttpStatus.FORBIDDEN);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exception = new ExceptionResponse(new Date(), "Validation Failed ",
				ex.getBindingResult().toString());
		return new ResponseEntity<Object>(exception, HttpStatus.BAD_REQUEST);
	}
}
