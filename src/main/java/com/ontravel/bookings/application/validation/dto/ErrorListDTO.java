package com.ontravel.bookings.application.validation.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorListDTO {

	private List<ErrorMessageDTO> errors;
	
	public static ErrorListDTO from(RuntimeException exception) {
		var error = ErrorMessageDTO.of(exception.getMessage());
		return new ErrorListDTO(List.of(error));
	}
		
	public static ErrorListDTO of(List<ErrorMessageDTO> errors) {
		return new ErrorListDTO(errors);
	}
	
}
