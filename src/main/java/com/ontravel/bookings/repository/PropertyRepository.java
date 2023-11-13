package com.ontravel.bookings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ontravel.bookings.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {

}
