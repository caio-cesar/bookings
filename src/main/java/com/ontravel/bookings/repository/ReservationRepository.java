package com.ontravel.bookings.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ontravel.bookings.entity.Reservation;
import com.ontravel.bookings.entity.enums.ReservationStatus;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	String RESERVATION_QUERY = " SELECT r1.property.id FROM Reservation r1 " +
			"  WHERE ((r1.startDate BETWEEN :startDate AND :endDate) OR " +
			"         (r1.endDate BETWEEN :startDate AND :endDate) OR " +
			"         (:startDate BETWEEN r1.startDate AND r1.endDate) OR " +
			"         (:endDate BETWEEN r1.startDate AND r1.endDate)) "
			+ " AND r1.property.id = :propertyId "
			+ " AND r1.status = :status ";

	String UPDATED_RESERVATION = " AND r1.id != :reservationId ";
	
	String PROPERTY_BLOCK_QUERY = " SELECT r2.property.id FROM PropertyBlock r2 " +
			"  WHERE ((r2.startDate BETWEEN :startDate AND :endDate) OR " +
			"         (r2.endDate BETWEEN :startDate AND :endDate) OR " +
			"         (:startDate BETWEEN r2.startDate AND r2.endDate) OR " +
			"         (:endDate BETWEEN r2.startDate AND r2.endDate)) "
			+ " AND r2.property.id = :propertyId ";
	
	String OVERLAPPING_RESERVATION_QUERY = " SELECT DISTINCT p.id FROM Property p WHERE p.id IN (" 
	+ RESERVATION_QUERY + ") OR p.id IN (" + PROPERTY_BLOCK_QUERY + ") ";
	
	String OVERLAPPING_RESERVATION_QUERY_UPDATED = " SELECT DISTINCT p.id FROM Property p WHERE p.id IN (" 
	+ RESERVATION_QUERY + UPDATED_RESERVATION + ") OR p.id IN (" + PROPERTY_BLOCK_QUERY + ") ";
	
	@Query(OVERLAPPING_RESERVATION_QUERY)
	Optional<Long> hasOverlappingReservationsOrBlocks(
			@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate,
			@Param("propertyId") Long propertyId,
			@Param("status") ReservationStatus status);
	
	
	@Query(OVERLAPPING_RESERVATION_QUERY_UPDATED)
	Optional<Long> hasOverlappingReservationsOrBlocks(
			@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate,
			@Param("propertyId") Long propertyId,
			@Param("status") ReservationStatus status,
			@Param("reservationId") Long reservationId);

}
