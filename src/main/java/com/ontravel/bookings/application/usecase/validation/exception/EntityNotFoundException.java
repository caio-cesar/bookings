package com.ontravel.bookings.application.usecase.validation.exception;

public class EntityNotFoundException extends BusinessException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4048783362550543965L;

	public EntityNotFoundException(String message) {
		super(message);
	}

}
