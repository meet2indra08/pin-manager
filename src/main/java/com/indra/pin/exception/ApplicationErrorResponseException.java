package com.indra.pin.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * Gets the developer message.
 *
 * @return the developer message
 */
@Getter

/**
 * Sets the developer message.
 *
 * @param developerMessage the new developer message
 */
@Setter

/**
 * Instantiates a new application error response exception.
 */

/**
 * Instantiates a new application error response exception.
 */
@NoArgsConstructor
public abstract class ApplicationErrorResponseException extends RuntimeException implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The status. */
	private int status;

	/** The code. */
	private String code;

	/** The message. */
	private String message;

	/** The developer message. */
	private String developerMessage;
	
	/** The errorMessageCode */
	private String errorMessageCode;

	/**
	 * Instantiates a new application error response exception.
	 *
	 * @param message
	 *            the message
	 */
	public ApplicationErrorResponseException(String message) {
		super(message);
		this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

	/**
	 * Instantiates a new application error response exception.
	 *
	 * @param message
	 *            the message.
	 * @param errorMessageCode
	 *            the errorMessageCode.           
	 */
	public ApplicationErrorResponseException(String message, String errorMessageCode) {
		super(message);
		this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
		this.errorMessageCode = errorMessageCode;
	}
	
	
	/**
	 * Instantiates a new application error response exception.
	 *
	 * @param message
	 *            the message
	 * @param throwable
	 *            the throwable
	 */
	public ApplicationErrorResponseException(String message, Throwable throwable) {
		super(message, throwable);
		this.developerMessage = throwable.getMessage();
	}
	
	
	/**
	 * Instantiates a new application error response exception.
	 *
	 * @param message
	 *            the message
	 * @param throwable
	 *            the throwable
	 * @param errorMessageCode
	 *            the errorMessageCode
	 */
	public ApplicationErrorResponseException(String message, Throwable throwable, String errorMessageCode) {
		super(message, throwable);
		this.developerMessage = throwable.getMessage();
		this.errorMessageCode = errorMessageCode;
	}


}
