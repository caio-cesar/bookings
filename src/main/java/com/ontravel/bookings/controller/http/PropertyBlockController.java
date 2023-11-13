package com.ontravel.bookings.controller.http;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ontravel.bookings.application.usecase.PropertyBlockService;
import com.ontravel.bookings.controller.util.ControllerPath;
import com.ontravel.bookings.dto.CreatePropertyBlockInputDTO;
import com.ontravel.bookings.dto.PropertyBlockDTO;

@RestController
@RequestMapping(ControllerPath.PROPERTIES_BLOCKS_PATH)
public class PropertyBlockController {

	@Autowired
	private PropertyBlockService service;
	
	@GetMapping
	public ResponseEntity<List<PropertyBlockDTO>> getAllPropertiesBLocks() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@PostMapping
	public ResponseEntity<PropertyBlockDTO> create(@RequestBody CreatePropertyBlockInputDTO input) {
		var created = service.create(input);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
}
