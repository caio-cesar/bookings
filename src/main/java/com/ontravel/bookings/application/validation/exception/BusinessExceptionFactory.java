package com.ontravel.bookings.application.validation.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BusinessExceptionFactory {

	public static EntityNotFoundException createEntityNotFound() {
		return new EntityNotFoundException("Entity not found");
	}
	
	public static EntityNotFoundException createPropertyNotFound() {
		return new EntityNotFoundException("Property not found");
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
	
	public static BusinessException createPastDateException() {
		return new PastDateException("Please choose a date in the future for your reservation");
	}
	
	public static BusinessException createInvalidRebookingException() {
		return new InvalidPeriodException("Only active and cancelled reservations can be rebooked.");
	}
	
	public static BusinessException create(String message) {
		return new BusinessException(message);
	}
	
}
