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

import com.ontravel.bookings.application.usecase.PropertyService;
import com.ontravel.bookings.controller.util.ControllerPath;
import com.ontravel.bookings.dto.CreatePropertyInputDTO;
import com.ontravel.bookings.dto.PropertyDTO;

@RestController
@RequestMapping(ControllerPath.PROPERTIES_PATH)
public class PropertyController {

	@Autowired
	private PropertyService service;
	
	@GetMapping
	public ResponseEntity<List<PropertyDTO>> getAllProperties() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@PostMapping
	public ResponseEntity<PropertyDTO> create(@RequestBody @Valid CreatePropertyInputDTO input) {
		var created = service.create(input);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
}
