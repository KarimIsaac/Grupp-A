package com.example.gruppajava.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.gruppajava.entity.ParkSlot;
import com.example.gruppajava.entity.ParkingPriceZone;
import com.example.gruppajava.repository.ParkSlotRepository;
import com.example.gruppajava.repository.ParkingPriceZoneRepository;

@RestController
public class ParkSlotController {

  private final ParkSlotRepository parkslotRepo;
  private final ParkingPriceZoneRepository priceZoneRepo;

  public ParkSlotController(ParkSlotRepository parkslotRepo, ParkingPriceZoneRepository priceZoneRepo) {
    this.parkslotRepo = parkslotRepo;
    this.priceZoneRepo = priceZoneRepo;
  }

  // GET GET GET GET
  // return all park slots
  @GetMapping("/api/parkslots")
  public List<ParkSlot> getAllParkSlots() {
    return parkslotRepo.findAll();
  }

  // return one park slot depending on (id) parameter
  @GetMapping("/api/parkslots/{id}")
  public ParkSlot getParkSlot(@PathVariable Long id) {
    return parkslotRepo.findById(id).orElse(null);
  }

  // return list of park slots depending on (available) parameter /api/parkslot/?available=true or false
  @GetMapping("/api/parkslots/")
  public List<ParkSlot> getAllConditionParkSlots(@RequestParam boolean available) {
    return parkslotRepo.findAllByAvailable(available);
  }

  // POST POST POST POST
  @PostMapping("/api/parkslots")
  public ResponseEntity<ParkSlot> addParkSlot(@RequestBody AddParkSlotReq req) {
    ParkingPriceZone priceZone = priceZoneRepo.findById(req.zone_id).orElse(null);
    if (priceZone == null) {
      return ResponseEntity.notFound().build();
    }

    ParkSlot parkslot = new ParkSlot(priceZone, req.available);

    parkslotRepo.save(parkslot);

    URI location = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(parkslot.getId())
      .toUri();
    return ResponseEntity.created(location).body(parkslot);
  }

  // PATCH PATCH PATCH PATCH
  @PatchMapping("/api/parkslots/{id}")
  public ResponseEntity<ParkSlot> modParkSlot(@PathVariable Long id) {
    ParkSlot parkSlot = parkslotRepo.findById(id).orElse(null);
    if (parkSlot == null) {
      return ResponseEntity.notFound().build();
    }

    parkSlot.setAvailable(!parkSlot.getAvailable());
    parkslotRepo.save(parkSlot);
    return ResponseEntity.ok(parkSlot);
  }

  record AddParkSlotReq(
    long zone_id,
    boolean available
  ){}
}

