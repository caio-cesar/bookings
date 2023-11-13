package com.ontravel.bookings.infra.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ontravel.bookings.application.usecase.validation.BusinessException;
import com.ontravel.bookings.application.usecase.validation.dto.ErrorListDTO;

@ControllerAdvice
public class ResponseEntityExceptionHandler {

	@ExceptionHandler(BusinessException.class) 
	public ResponseEntity<ErrorListDTO> handleBusinessExceptions(BusinessException exception) {
		return ResponseEntity.badRequest().body(ErrorListDTO.from(exception));
	}
	
}
