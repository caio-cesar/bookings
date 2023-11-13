package com.ontravel.bookings.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PropertyBlockDTO {
	
	private Long id;
	
	private PropertyDTO property;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private String reason;
	
}
