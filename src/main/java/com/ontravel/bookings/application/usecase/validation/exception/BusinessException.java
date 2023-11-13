package com.ontravel.bookings.application.usecase.validation.exception;

public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8729293814117481952L;

	public BusinessException(String message) {
		super(message);
	}
	
}
