package com.ontravel.bookings.application.validation.exception;

public class PastDateException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4068570056846431298L;

	public PastDateException(String message) {
		super(message);
	}

}
