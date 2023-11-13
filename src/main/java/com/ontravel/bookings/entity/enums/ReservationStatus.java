package com.ontravel.bookings.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationStatus {
	
	ACTIVE(1),
	
	REBOOKED(2),
	
	CANCELLED(3);
	
	private int statusCode;
	
	public static ReservationStatus of(Integer value) {
		switch(value) {
		case 1:
			return ACTIVE;
		case 2: 
			return REBOOKED;
		case 3:
			return CANCELLED;
		default:
			throw new IllegalArgumentException("Invalid status value: " + value);
		}
	}
	
}
