package com.ontravel.bookings.application.usecase.validation;

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
	
	public static BusinessException create(String message) {
		return new BusinessException(message);
	}
}
