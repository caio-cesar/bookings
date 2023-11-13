package com.ontravel.bookings.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ontravel.bookings.application.usecase.validation.PeriodValidator;
import com.ontravel.bookings.application.usecase.validation.exception.BusinessExceptionFactory;
import com.ontravel.bookings.dto.CreatePropertyBlockInputDTO;
import com.ontravel.bookings.dto.PropertyBlockDTO;
import com.ontravel.bookings.dto.UpdatePropertyBlockInputDTO;
import com.ontravel.bookings.entity.Property;
import com.ontravel.bookings.entity.PropertyBlock;
import com.ontravel.bookings.mapper.PropertyBlockMapper;
import com.ontravel.bookings.repository.PropertyBlockRepository;
import com.ontravel.bookings.repository.PropertyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PropertyBlockService {
	
	private final PropertyBlockRepository repository;
	
	private final PropertyBlockMapper mapper;
	
	private final PropertyRepository propertyRepository;
	
	public PropertyBlockDTO create(CreatePropertyBlockInputDTO input) {
		PeriodValidator.validate(input.getStartDate(), input.getEndDate());
		var property = findPropertyById(input.getPropertyId());
		var entity = mapper.inputToEntity(input, property);
		return saveAndConvertToDTO(entity);
	}
	
	public List<PropertyBlockDTO> findAll() {
		return mapper.toDTO(repository.findAll());
	}
	
	public PropertyBlockDTO update(Long id, UpdatePropertyBlockInputDTO input) {
		validateUpdateInput(id, input);
		var property = findPropertyById(input.getPropertyId());
		var entity = mapper.inputToEntity(input, property);
		return saveAndConvertToDTO(entity);
	}

	private void validateUpdateInput(Long id, UpdatePropertyBlockInputDTO input) {
		PeriodValidator.validate(input.getStartDate(), input.getEndDate());
		validateEntityNotFound(id);
	}

	private PropertyBlockDTO saveAndConvertToDTO(PropertyBlock entity) {
		entity = repository.save(entity);
		return mapper.toDTO(entity);
	}
	
	public void delete(Long id) {
		validateEntityNotFound(id);
		repository.deleteById(id);
	}
	
	private void validateEntityNotFound(Long id) {
		if (!repository.existsById(id)) {
			throw BusinessExceptionFactory.createEntityNotFound();
		}
	}
	
	private Property findPropertyById(Long id) {
		return propertyRepository.findById(id).orElseThrow(BusinessExceptionFactory::createPropertyNotFound);
	}

}
