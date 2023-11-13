package com.ontravel.bookings.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ontravel.bookings.dto.CreatePropertyInputDTO;
import com.ontravel.bookings.dto.PropertyDTO;
import com.ontravel.bookings.mapper.PropertyMapper;
import com.ontravel.bookings.repository.PropertyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PropertyService {

	private final PropertyRepository repository;
	
	private final PropertyMapper mapper;
	
	public List<PropertyDTO> findAll() {
		return mapper.toDTO(repository.findAll());
	}
	
	public PropertyDTO create(CreatePropertyInputDTO input) {
		var property = mapper.inputToEntity(input);
		property = repository.save(property);
		return mapper.toDTO(property);
	}
	
}
