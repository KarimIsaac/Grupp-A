package com.example.gruppajava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gruppajava.entity.ParkSlot;

public interface ParkSlotRepository extends JpaRepository<ParkSlot,Long>{
  List<ParkSlot> findAllByAvailable(boolean available);
}
