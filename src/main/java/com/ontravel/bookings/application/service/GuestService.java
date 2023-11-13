package com.ontravel.bookings.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ontravel.bookings.dto.CreateGuestInputDTO;
import com.ontravel.bookings.dto.GuestDTO;
import com.ontravel.bookings.mapper.GuestMapper;
import com.ontravel.bookings.repository.GuestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GuestService {
	
	private final GuestRepository repository;
	
	private final GuestMapper mapper;
	
	public GuestDTO create(CreateGuestInputDTO input) {
		var guest = mapper.inputToEntity(input);
		guest = repository.save(guest);
		return mapper.toDTO(guest);
	}
	
	public List<GuestDTO> findAll() {
		return mapper.toDTO(repository.findAll());
	}
	
}
