package com.ontravel.bookings.application.usecase.validation.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BusinessExceptionFactory {

	public static EntityNotFoundException createEntityNotFound() {
		return new EntityNotFoundException("Entity not found");
	}

	public static BusinessException createOverlappingDateException() {
		return new OverlappingDateException("Unavailable period. Please choose a different one.");
	}
	
	public static BusinessException createInvalidCancellationExcepion() {
		return new OverlappingDateException("You can only cancel active reservations.");
	}
	
	public static BusinessException createInvalidPeriodException() {
		return new InvalidPeriodException("Invalid period. Start date must be before or equal to the end date.");
	}
	
	public static BusinessException create(String message) {
		return new BusinessException(message);
	}
	
}
