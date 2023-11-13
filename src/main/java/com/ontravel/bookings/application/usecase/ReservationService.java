package com.ontravel.bookings.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ontravel.bookings.application.usecase.validation.BusinessException;
import com.ontravel.bookings.dto.CreateReservationInputDTO;
import com.ontravel.bookings.dto.ReservationDTO;
import com.ontravel.bookings.entity.enums.ReservationStatus;
import com.ontravel.bookings.mapper.ReservationMapper;
import com.ontravel.bookings.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository repository;
	
	private final ReservationMapper mapper;
	
	public List<ReservationDTO> findAll() {
		return mapper.toDTO(repository.findAll());
	}
	
	public ReservationDTO create(CreateReservationInputDTO input) {
		
		if (repository.hasOverlappingReservationsOrBlocks(
				input.getStartDate(), 
				input.getEndDate(), 
				input.getPropertyId(), 
				ReservationStatus.ACTIVE).isPresent()) {
			throw new BusinessException("Overlapping dates. Try choosing a different one");
		}
		
		var property = mapper.inputToEntity(input);
		property = repository.save(property);
		
		return mapper.toDTO(property);
	}
	
}
