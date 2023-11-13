package com.ontravel.bookings.application.usecase.validation.exception;

public class OverlappingDateException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1125348306516516417L;

	public OverlappingDateException(String message) {
		super(message);
	}

}
