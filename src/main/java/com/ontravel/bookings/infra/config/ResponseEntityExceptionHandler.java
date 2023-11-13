package com.ontravel.bookings.infra.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ontravel.bookings.application.usecase.validation.dto.ErrorListDTO;
import com.ontravel.bookings.application.usecase.validation.dto.ErrorMessageDTO;
import com.ontravel.bookings.application.usecase.validation.exception.BusinessException;
import com.ontravel.bookings.application.usecase.validation.exception.EntityNotFoundException;

@ControllerAdvice
public class ResponseEntityExceptionHandler {

	@ExceptionHandler(BusinessException.class) 
	public ResponseEntity<ErrorListDTO> handleBusinessExceptions(BusinessException exception) {
		return ResponseEntity.badRequest().body(ErrorListDTO.from(exception));
	}
	
	@ExceptionHandler(EntityNotFoundException.class) 
	public ResponseEntity<ErrorListDTO> handleNotFound(BusinessException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorListDTO.from(exception));
	}
	
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorListDTO> handleValidationException(MethodArgumentNotValidException ex) {
        List<ErrorMessageDTO> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> ErrorMessageDTO.of(error.getField() + ": " + error.getDefaultMessage()))
            .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(ErrorListDTO.of(errors));
    }
	
    @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
    public ResponseEntity<ErrorListDTO> handleEntityNotFound(javax.persistence.EntityNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorListDTO.from(exception));
    }

}
