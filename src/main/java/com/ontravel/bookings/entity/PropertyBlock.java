package com.ontravel.bookings.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="PROPERTY_BLOCK")
public class PropertyBlock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@OneToOne
	private Property property;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private String reason;
	
}
