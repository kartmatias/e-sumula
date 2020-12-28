package br.com.sumulaesportiva.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = TestNotFoundException.REASON)
public class TestNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	protected static final String REASON = "Test n\u00e3o foi encontrado.";

	public TestNotFoundException() {
		super(REASON);
	}

}
