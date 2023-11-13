package com.ontravel.bookings.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ontravel.bookings.application.usecase.validation.BusinessExceptionFactory;
import com.ontravel.bookings.dto.CreateReservationInputDTO;
import com.ontravel.bookings.dto.ReservationDTO;
import com.ontravel.bookings.dto.UpdateReservationInputDTO;
import com.ontravel.bookings.entity.Reservation;
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
			
			throw BusinessExceptionFactory.createOverlappingDateException();
		}
		
		var property = repository.save(mapper.inputToEntity(input));
		return mapper.toDTO(findById(property.getId()));
	}
	
	public ReservationDTO update(Long id, UpdateReservationInputDTO input) {

		if (repository.hasOverlappingReservationsOrBlocks(
				input.getStartDate(), 
				input.getEndDate(), 
				input.getPropertyId(), 
				ReservationStatus.ACTIVE,
				id).isPresent()) {
			
			throw BusinessExceptionFactory.createOverlappingDateException();
		}
		
		var entity = mapper.inputToEntity(input, id);
		var property = repository.save(entity);
		return mapper.toDTO(property);
	}
	
	public ReservationDTO cancel(Long id) {
		var entity = findById(id);
		
		if (entity.getStatus() != ReservationStatus.ACTIVE) {
			throw BusinessExceptionFactory.createInvalidCancellationExcepion();
		}
		
		entity.setStatus(ReservationStatus.CANCELLED);
		entity = repository.save(entity);
		return mapper.toDTO(entity);
	}
	
	private Reservation findById(Long id) {
		return repository.findById(id)
				.orElseThrow(BusinessExceptionFactory::createEntityNotFound);
	}
	
}
