package com.ontravel.bookings.application.usecase.validation.dto;

import java.util.List;

import com.ontravel.bookings.application.usecase.validation.exception.BusinessException;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorListDTO {

	private List<ErrorMessageDTO> errors;
	
	public static ErrorListDTO from(BusinessException exception) {
		var error = ErrorMessageDTO.of(exception.getMessage());
		return new ErrorListDTO(List.of(error));
	}
		
}
