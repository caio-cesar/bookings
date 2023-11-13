package com.ontravel.bookings.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ontravel.bookings.dto.CreatePropertyBlockInputDTO;
import com.ontravel.bookings.dto.PropertyBlockDTO;
import com.ontravel.bookings.mapper.PropertyBlockMapper;
import com.ontravel.bookings.repository.PropertyBlockRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PropertyBlockService {
	
	private final PropertyBlockRepository repository;
	
	private final PropertyBlockMapper mapper;
	
	public PropertyBlockDTO create(CreatePropertyBlockInputDTO input) {
		var guest = mapper.inputToEntity(input);
		guest = repository.save(guest);
		return mapper.toDTO(guest);
	}
	
	public List<PropertyBlockDTO> findAll() {
		return mapper.toDTO(repository.findAll());
	}
	
}
