package com.ontravel.bookings.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.ontravel.bookings.dto.CreateGuestInputDTO;
import com.ontravel.bookings.dto.GuestDTO;
import com.ontravel.bookings.entity.Guest;

@Mapper(componentModel = "spring")
public interface GuestMapper {
	
	GuestDTO toDTO(Guest property);
	
	List<GuestDTO> toDTO(List<Guest> guests);
	
	Guest inputToEntity(CreateGuestInputDTO input);
	
}
