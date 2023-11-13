package com.ontravel.bookings.application.validation;

import java.time.LocalDate;

import com.ontravel.bookings.application.validation.exception.BusinessExceptionFactory;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PeriodValidator {

	public static void validate(LocalDate startDate, LocalDate endDate) {
		if (endDate.isBefore(startDate)) {
			throw BusinessExceptionFactory.createInvalidPeriodException();
		} else if (startDate.isBefore(LocalDate.now())) {
			throw BusinessExceptionFactory.createPastDateException();
		}
	}
	
}
