package com.indra.pin.exception;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4571451418690379635L;

	public DataNotFoundException(Long id) {
		super("Book not found for id=" + id);
	}
}
