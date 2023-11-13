package com.ontravel.bookings.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ontravel.bookings.entity.enums.ReservationStatus;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationDTO {
	
	private Long id;
	
	private ReservationStatus status;
	
	private PropertyDTO property;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private List<GuestDTO> guests;
	
	private String observation;
	
	private Long rebookedId;
	
}
