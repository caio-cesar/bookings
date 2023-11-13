package com.ontravel.bookings.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreatePropertyBlockInputDTO {
	
	@NotNull
	private Long propertyId;
	
	@NotNull
	private LocalDate startDate;
	
	@NotNull
	private LocalDate endDate;
	
	@NotBlank
	private String reason;
	
}
