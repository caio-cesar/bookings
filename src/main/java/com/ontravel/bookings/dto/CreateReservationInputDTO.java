package com.ontravel.bookings.dto;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateReservationInputDTO {

	@NotNull
	private Long propertyId; 
	
	@NotNull
	private LocalDate startDate;
	
	@NotNull
	private LocalDate endDate;
	
	@NotEmpty
	private Set<GuestDTO> guests;
	
	private String observation;
	
}
