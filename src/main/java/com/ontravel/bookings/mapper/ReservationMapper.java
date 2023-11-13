package com.ontravel.bookings.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ontravel.bookings.dto.CreateReservationInputDTO;
import com.ontravel.bookings.dto.ReservationDTO;
import com.ontravel.bookings.entity.Reservation;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
	
	ReservationDTO toDTO(Reservation reservation);
	
	List<ReservationDTO> toDTO(List<Reservation> reservations);
	
	@Mapping(target = "property.id", source = "input.propertyId")
	Reservation inputToEntity(CreateReservationInputDTO input);
	
}
