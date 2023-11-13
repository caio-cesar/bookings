package com.ontravel.bookings.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreatePropertyInputDTO {

	@NotBlank
	private String name;
	
	@NotBlank
	private String address;
	
}
