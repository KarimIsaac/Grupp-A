package com.example.gruppajava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gruppajava.entity.ParkingEvent;

public interface ParkingEventRepository extends JpaRepository<ParkingEvent, Long> {

    Iterable<ParkingEvent> findByUserId(Long id);

    Iterable<ParkingEvent> findByCarId(Long id);

    // When parkingslot entity exists dont forget to add it here.
    
}
