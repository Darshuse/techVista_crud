package com.techVista.crud.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class DepartmentForbiddenDeleteException extends RuntimeException {

	public DepartmentForbiddenDeleteException(String message) {
		super(message);
	}
}
