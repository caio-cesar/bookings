package com.ontravel.bookings.dto;

import java.time.LocalDate;
 
public interface OverlappingReservationDTO {

	Long getReservationId();
	
	LocalDate getStartDate();
	
	LocalDate getEndDate();
	
}
