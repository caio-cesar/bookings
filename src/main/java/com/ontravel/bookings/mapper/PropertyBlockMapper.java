package com.ontravel.bookings.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ontravel.bookings.dto.CreatePropertyBlockInputDTO;
import com.ontravel.bookings.dto.PropertyBlockDTO;
import com.ontravel.bookings.dto.UpdatePropertyBlockInputDTO;
import com.ontravel.bookings.entity.Property;
import com.ontravel.bookings.entity.PropertyBlock;

@Mapper(componentModel = "spring")
public interface PropertyBlockMapper {
	
	PropertyBlockDTO toDTO(PropertyBlock property);
	
	List<PropertyBlockDTO> toDTO(List<PropertyBlock> property);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "property", source = "property")
	PropertyBlock inputToEntity(CreatePropertyBlockInputDTO input, Property property);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "property", source = "property")
	PropertyBlock inputToEntity(UpdatePropertyBlockInputDTO input, Property property);
	
}
