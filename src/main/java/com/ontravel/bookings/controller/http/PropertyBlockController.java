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

import com.ontravel.bookings.application.usecase.PropertyBlockService;
import com.ontravel.bookings.controller.util.ControllerPath;
import com.ontravel.bookings.dto.CreatePropertyBlockInputDTO;
import com.ontravel.bookings.dto.PropertyBlockDTO;
import com.ontravel.bookings.dto.UpdatePropertyBlockInputDTO;

@RestController
@RequestMapping(ControllerPath.PROPERTIES_BLOCKS_PATH)
public class PropertyBlockController {

	@Autowired
	private PropertyBlockService service;
	
	@PostMapping
	public ResponseEntity<PropertyBlockDTO> create(@RequestBody @Valid CreatePropertyBlockInputDTO input) {
		var created = service.create(input);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@GetMapping
	public ResponseEntity<List<PropertyBlockDTO>> getAllPropertiesBLocks() {
		return ResponseEntity.ok(service.findAll());
	}

	@PutMapping("/{id}")
	public ResponseEntity<PropertyBlockDTO> update(
			@PathVariable Long id, 
			@RequestBody @Valid UpdatePropertyBlockInputDTO input) {
		var updated = service.update(id, input);
		return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> cancel(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
