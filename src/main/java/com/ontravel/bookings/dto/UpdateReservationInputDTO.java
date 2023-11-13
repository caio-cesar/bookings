package com.ontravel.bookings.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class UpdateReservationInputDTO {
	
	private Long propertyId; 
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private List<GuestDTO> guests;
	
	private String observation;
	
}
