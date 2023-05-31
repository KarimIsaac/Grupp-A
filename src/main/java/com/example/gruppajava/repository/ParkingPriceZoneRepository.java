package com.example.gruppajava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gruppajava.entity.ParkingPriceZone;

@Repository
public interface ParkingPriceZoneRepository extends JpaRepository<ParkingPriceZone, Long> {
    List<ParkingPriceZone> findByStatus(String status);
}