package com.ontravel.bookings.application.usecase.validation;

public class InvalidCancellationException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7386416604439605505L;

	public InvalidCancellationException(String message) {
		super(message);
	}

}
