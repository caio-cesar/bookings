package com.ontravel.bookings.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ontravel.bookings.dto.CreateReservationInputDTO;
import com.ontravel.bookings.dto.ReservationDTO;
import com.ontravel.bookings.dto.UpdateReservationInputDTO;
import com.ontravel.bookings.entity.Property;
import com.ontravel.bookings.entity.Reservation;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
	
	@Mapping(target = "rebookedId", source = "rebooked.id")
	ReservationDTO toDTO(Reservation reservation);
	
	List<ReservationDTO> toDTO(List<Reservation> reservations);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "property", source = "property")
	Reservation inputToEntity(CreateReservationInputDTO input, Property property);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "property", source = "property")
	Reservation inputToEntity(UpdateReservationInputDTO input, Long id, Property property);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "property", source = "property")
	Reservation inputToEntity(UpdateReservationInputDTO input, Property property);
	
}
