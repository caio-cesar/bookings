package com.ontravel.bookings.application.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ontravel.bookings.application.validation.PeriodValidator;
import com.ontravel.bookings.application.validation.exception.BusinessExceptionFactory;
import com.ontravel.bookings.dto.CreateReservationInputDTO;
import com.ontravel.bookings.dto.ReservationDTO;
import com.ontravel.bookings.dto.UpdateReservationInputDTO;
import com.ontravel.bookings.entity.Property;
import com.ontravel.bookings.entity.Reservation;
import com.ontravel.bookings.entity.enums.ReservationStatus;
import com.ontravel.bookings.mapper.ReservationMapper;
import com.ontravel.bookings.repository.PropertyRepository;
import com.ontravel.bookings.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository repository;

	private final ReservationMapper mapper;
	
	private final PropertyRepository propertyRepository;

	
	public List<ReservationDTO> findAll() {
		return mapper.toDTO(repository.findAll());
	}

	public ReservationDTO create(CreateReservationInputDTO input) {
		validateCreateInput(input);
		var property = findPropertyById(input.getPropertyId());
		var reservation = repository.save(mapper.inputToEntity(input, property));
		return mapper.toDTO(reservation);
	}

	private void validateCreateInput(CreateReservationInputDTO input) {
		PeriodValidator.validate(input.getStartDate(), input.getEndDate());
		validateOverlappingDate(input.getStartDate(), input.getEndDate(), input.getPropertyId());
	}

	private void validateOverlappingDate(LocalDate startDate, LocalDate endDate, Long propertyId) {
		validateOverlappingDate(startDate, endDate, propertyId, null);
	}

	private void validateOverlappingDate(LocalDate startDate, LocalDate endDate, 
			Long propertyId, Long reservationId) {
		
		var result = reservationId == null ? 
			repository.hasOverlappingReservationsOrBlocks(
				startDate, 
				endDate, 
				propertyId, 
				ReservationStatus.ACTIVE) : 

			repository.hasOverlappingReservationsOrBlocks(
							startDate, 
							startDate, 
							propertyId, 
							ReservationStatus.ACTIVE, 
							reservationId);


		if (result.isPresent()) {
			throw BusinessExceptionFactory.createOverlappingDateException();
		}
	}

	public ReservationDTO update(Long id, UpdateReservationInputDTO input) {
		validateUpdateInput(id, input);
		var property = findPropertyById(input.getPropertyId());
		var entity = mapper.inputToEntity(input, id, property);
		var reservation = repository.save(entity);
		return mapper.toDTO(reservation);
	}

	private void validateUpdateInput(Long id, UpdateReservationInputDTO input) {
		validateEntityNotFound(id);
		PeriodValidator.validate(input.getStartDate(), input.getEndDate());
		validateOverlappingDate(input.getStartDate(), input.getEndDate(), input.getPropertyId(), id);
	}

	private void validateRebookInput(UpdateReservationInputDTO input) {
		PeriodValidator.validate(input.getStartDate(), input.getEndDate());
		validateOverlappingDate(input.getStartDate(), input.getEndDate(), input.getPropertyId());
	}

	@Transactional
	public ReservationDTO rebook(Long id, UpdateReservationInputDTO input) {
		var reservation = findById(id);
		validateRebookInput(input);
		validateRebookStatus(reservation);
		var property = findPropertyById(input.getPropertyId());
		var entity = mapper.inputToEntity(input, property);
		entity = repository.save(entity);
		reservation.setStatus(ReservationStatus.REBOOKED);
		reservation.setRebooked(entity);
		repository.save(reservation);
		return mapper.toDTO(entity);
	}

	private void validateRebookStatus(Reservation reservation) {
		if (reservation.getStatus() == ReservationStatus.REBOOKED) {
			throw BusinessExceptionFactory.createInvalidRebookingException();
		}
	}

	public ReservationDTO cancel(Long id) {
		var entity = findById(id);
		validateCancellationStatus(entity);
		entity.setStatus(ReservationStatus.CANCELLED);
		entity = repository.save(entity);
		return mapper.toDTO(entity);
	}

	private void validateCancellationStatus(Reservation entity) {
		if (entity.getStatus() != ReservationStatus.ACTIVE) {
			throw BusinessExceptionFactory.createInvalidCancellationExcepion();
		}
	}

	private Reservation findById(Long id) {
		return repository.findById(id).orElseThrow(BusinessExceptionFactory::createEntityNotFound);
	}
	
	private Property findPropertyById(Long id) {
		return propertyRepository.findById(id).orElseThrow(BusinessExceptionFactory::createPropertyNotFound);
	}
	
	private void validateEntityNotFound(Long id) {
		if (!repository.existsById(id)) {
			throw BusinessExceptionFactory.createEntityNotFound();
		}
	}
	
}
