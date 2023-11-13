package com.ontravel.bookings.application.usecase.validation;

import java.time.LocalDate;

import com.ontravel.bookings.application.usecase.validation.exception.BusinessExceptionFactory;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PeriodValidator {

	public static void validate(LocalDate startDate, LocalDate endDate) {
		if (endDate.isBefore(startDate)) {
			throw BusinessExceptionFactory.createInvalidPeriodException();
		}
	}
	
}
