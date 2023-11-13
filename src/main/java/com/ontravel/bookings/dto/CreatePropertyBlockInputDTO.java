package com.ontravel.bookings.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CreatePropertyBlockInputDTO {
	
	private Long propertyId;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private String reason;
	
}
