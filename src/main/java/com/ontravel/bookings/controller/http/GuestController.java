package com.ontravel.bookings.controller.http;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ontravel.bookings.application.service.GuestService;
import com.ontravel.bookings.controller.util.ControllerPath;
import com.ontravel.bookings.dto.CreateGuestInputDTO;
import com.ontravel.bookings.dto.GuestDTO;

@RestController
@RequestMapping(ControllerPath.GUESTS_PATH)
public class GuestController {

	@Autowired
	private GuestService service;
	
	@PostMapping
	public ResponseEntity<GuestDTO> create(@RequestBody @Valid CreateGuestInputDTO input) {
		var created = service.create(input);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@GetMapping
	public ResponseEntity<List<GuestDTO>> getAllGuests() {
		return ResponseEntity.ok(service.findAll());
	}

}
