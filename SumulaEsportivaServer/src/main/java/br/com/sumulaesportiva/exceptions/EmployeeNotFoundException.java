package br.com.sumulaesportiva.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = EmployeeNotFoundException.REASON)
public class EmployeeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	protected static final String REASON = "Employee n\u00e3o foi encontrado.";

	public EmployeeNotFoundException() {
		super(REASON);
	}
}
