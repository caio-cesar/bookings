package com.ontravel.bookings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ontravel.bookings.entity.Guest;

public interface GuestRepository extends JpaRepository<Guest, Long> {

}
