package com.ontravel.bookings.controller.http;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ontravel.bookings.application.usecase.ReservationService;
import com.ontravel.bookings.controller.util.ControllerPath;
import com.ontravel.bookings.dto.CreateReservationInputDTO;
import com.ontravel.bookings.dto.ReservationDTO;
import com.ontravel.bookings.dto.UpdateReservationInputDTO;

@RestController
@RequestMapping(ControllerPath.RESERVATIONS_PATH)
public class ReservationController {

	@Autowired
	private ReservationService service;
	
	
	@PostMapping
	public ResponseEntity<ReservationDTO> create(@RequestBody @Valid CreateReservationInputDTO input) {
		var created = service.create(input);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@GetMapping
	public ResponseEntity<List<ReservationDTO>> getAllReservations() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ReservationDTO> update(
			@PathVariable Long id, 
			@RequestBody @Valid UpdateReservationInputDTO input) {
		var updated = service.update(id, input);
		return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ReservationDTO> cancel(@PathVariable Long id) {
		var cancelled = service.cancel(id);
		return ResponseEntity.ok(cancelled);
	}
	
}
