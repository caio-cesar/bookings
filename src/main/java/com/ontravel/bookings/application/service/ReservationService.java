package com.ontravel.bookings.application.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ontravel.bookings.application.validation.PeriodValidator;
import com.ontravel.bookings.application.validation.exception.BusinessExceptionFactory;
import com.ontravel.bookings.dto.CreateReservationInputDTO;
import com.ontravel.bookings.dto.GuestDTO;
import com.ontravel.bookings.dto.ReservationDTO;
import com.ontravel.bookings.dto.UpdateReservationInputDTO;
import com.ontravel.bookings.entity.Guest;
import com.ontravel.bookings.entity.Property;
import com.ontravel.bookings.entity.Reservation;
import com.ontravel.bookings.entity.enums.ReservationStatus;
import com.ontravel.bookings.mapper.ReservationMapper;
import com.ontravel.bookings.repository.GuestRepository;
import com.ontravel.bookings.repository.PropertyRepository;
import com.ontravel.bookings.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository repository;

	private final ReservationMapper mapper;
	
	private final PropertyRepository propertyRepository;

	private final GuestRepository guestRepository;
	
	
	public List<ReservationDTO> findAll() {
		return mapper.toDTO(repository.findAll());
	}
	
	@Transactional
	public ReservationDTO create(CreateReservationInputDTO input) {
		validateCreateInput(input);
		var reservation = mapper.inputToEntity(
				input, 
				findPropertyById(input.getPropertyId()), 
				findAllGuestsById(input.getGuests()));
		return mapper.toDTO(repository.save(reservation));
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
		var entity = mapper.inputToEntity(
				input, 
				id, 
				findPropertyById(input.getPropertyId()),
				findAllGuestsById(input.getGuests()));
		return mapper.toDTO(repository.save(entity));
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
		return mapper.toDTO(saveRebookedEntity(input, reservation));
	}

	private Reservation saveRebookedEntity(UpdateReservationInputDTO input, Reservation reservation) {
		var entity = mapper.inputToEntity(
				input, 
				findPropertyById(input.getPropertyId()),
				findAllGuestsById(input.getGuests()));
		
		entity = repository.save(entity);
		reservation.setStatus(ReservationStatus.REBOOKED);
		reservation.setRebooked(entity);
		repository.save(reservation);
		return entity;
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
		if (! repository.existsById(id)) {
			throw BusinessExceptionFactory.createEntityNotFound();
		}
	}
	
	private List<Guest> findAllGuestsById(Set<GuestDTO> guests) {
		return guestRepository.findAllById(guests.stream().map(g -> g.getId()).toList());
	}
	
}
