package com.ontravel.bookings.application.usecase.validation;

public class EntityNotFoundException extends BusinessException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4048783362550543965L;

	public EntityNotFoundException(String message) {
		super(message);
	}

}
