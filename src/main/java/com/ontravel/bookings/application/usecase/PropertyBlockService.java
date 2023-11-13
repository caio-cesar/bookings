package com.ontravel.bookings.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ontravel.bookings.application.usecase.validation.PeriodValidator;
import com.ontravel.bookings.application.usecase.validation.exception.BusinessExceptionFactory;
import com.ontravel.bookings.dto.CreatePropertyBlockInputDTO;
import com.ontravel.bookings.dto.PropertyBlockDTO;
import com.ontravel.bookings.dto.UpdatePropertyBlockInputDTO;
import com.ontravel.bookings.entity.PropertyBlock;
import com.ontravel.bookings.mapper.PropertyBlockMapper;
import com.ontravel.bookings.repository.PropertyBlockRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PropertyBlockService {
	
	private final PropertyBlockRepository repository;
	
	private final PropertyBlockMapper mapper;
	
	public PropertyBlockDTO create(CreatePropertyBlockInputDTO input) {
		PeriodValidator.validate(input.getStartDate(), input.getEndDate());
		var entity = mapper.inputToEntity(input);
		return saveAndConvertToDTO(entity);
	}
	
	public List<PropertyBlockDTO> findAll() {
		return mapper.toDTO(repository.findAll());
	}
	
	public PropertyBlockDTO update(Long id, UpdatePropertyBlockInputDTO input) {
		PeriodValidator.validate(input.getStartDate(), input.getEndDate());
		validateEntityNotFound(id);
		var entity = mapper.inputToEntity(input);
		return saveAndConvertToDTO(entity);
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

}
