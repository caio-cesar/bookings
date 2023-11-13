package com.ontravel.bookings.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ontravel.bookings.entity.enums.ReservationStatus;

import lombok.Data;

@Data
@Entity
@Table(name="RESERVATION")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@OneToOne
	private Property property;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private ReservationStatus status = ReservationStatus.ACTIVE;
	
	@OneToOne
	private Reservation rebooked;
	
	@ManyToMany
	private List<Guest> guests;
	
	private String observation;
	
}
