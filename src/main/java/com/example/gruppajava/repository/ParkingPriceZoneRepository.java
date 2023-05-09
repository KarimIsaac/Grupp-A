package com.example.gruppajava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gruppajava.entity.ParkingPriceZone;

@Repository
public interface ParkingPriceZoneRepository extends JpaRepository<ParkingPriceZone, Long> {
}