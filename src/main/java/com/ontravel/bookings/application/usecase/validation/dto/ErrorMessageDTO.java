package com.ontravel.bookings.application.usecase.validation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessageDTO {

	private String message;
	
	public static ErrorMessageDTO of(String message) {
		return new ErrorMessageDTO(message);
	}
	
}
