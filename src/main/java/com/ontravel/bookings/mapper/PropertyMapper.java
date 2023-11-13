package com.ontravel.bookings.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.ontravel.bookings.dto.CreatePropertyInputDTO;
import com.ontravel.bookings.dto.PropertyDTO;
import com.ontravel.bookings.entity.Property;

@Mapper(componentModel = "spring")
public interface PropertyMapper {

	PropertyDTO toDTO(Property property);
	
	List<PropertyDTO> toDTO(List<Property> property);
	
	Property inputToEntity(CreatePropertyInputDTO input);
}
