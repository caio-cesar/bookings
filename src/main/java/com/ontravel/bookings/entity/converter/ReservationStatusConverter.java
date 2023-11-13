package com.ontravel.bookings.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.ontravel.bookings.entity.enums.ReservationStatus;

@Converter(autoApply = true)
public class ReservationStatusConverter implements AttributeConverter<ReservationStatus, Integer> {
 
    @Override
    public Integer convertToDatabaseColumn(ReservationStatus status) {
    	if (status == null) {
    		return null;
    	}
    	return status.getStatusCode();
    }

    @Override
    public ReservationStatus convertToEntityAttribute(Integer code) {
        return ReservationStatus.of(code);
    }
    
}
