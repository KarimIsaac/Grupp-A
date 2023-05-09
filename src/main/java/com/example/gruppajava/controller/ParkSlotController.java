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
import com.example.gruppajava.repository.ParkSlotRepository;

@RestController
public class ParkSlotController {
  
  private final ParkSlotRepository parkslotRepo;

  public ParkSlotController(ParkSlotRepository parkslotRepo){
    this.parkslotRepo=parkslotRepo;
  }

  // GET GET GET GET
  // return all park slots
  @GetMapping("/api/parkslots")
  public List<ParkSlot> getAllParkSlots(){
  return parkslotRepo.findAll();
  }

  // return one park slot depending on (id) parameter
  @GetMapping("/api/parkslots/{id}")
  public ParkSlot getParkSlot(@PathVariable Long id) {
    return parkslotRepo.findById(id).get();
  }

  // return list of park slots depending on (available) parameter /api/parkslot/?available=true or false
  @GetMapping("/api/parkslots/")
  public List<ParkSlot> getAllConditionParkSlots(@RequestParam boolean available){
    return parkslotRepo.findAllByAvailable(available);
  }
  
  // POST POST POST POST
  record AddParkSlotReq(
    long zone_id, //ParkPriceZone zone,
    boolean available
  ){}

  // add a new park slot, (id) generated automatically
  @PostMapping("/api/parkslots")
  public ResponseEntity<ParkSlot> addParkSlot(@RequestBody AddParkSlotReq req){
    ParkSlot parkslot = new ParkSlot(
      req.zone_id,
      req.available
    );

    parkslotRepo.save(parkslot);
    
    URI location=ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(parkslot.getId())
      .toUri();
    return ResponseEntity.created(location).body(parkslot);
  }

  // PATCH PATCH PATCH PATCH
  // change the available condition of a park slot using it's (id)
  // /api/parkslot/{id} will reverse the available boolean 
  @PatchMapping("/api/parkslots/{id}")
  public ParkSlot modParkSlot(@PathVariable Long id) {
    var modParkSlot = parkslotRepo.findById(id).get(); //.isPresent()? parkslotRepo.findById(id).get():null;
    modParkSlot.setAvailable(!modParkSlot.getAvailable());
    parkslotRepo.save(modParkSlot);
    return modParkSlot;
  }

}
