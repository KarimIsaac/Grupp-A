package com.example.gruppajava.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.gruppajava.entity.ParkingPriceZone;
import com.example.gruppajava.repository.ParkingPriceZoneRepository;

@RestController
@RequestMapping("/api/")
public class ParkingPriceZoneController {
    @Autowired
    private ParkingPriceZoneRepository parkingPriceZoneRepository;

    // GET GET GET GET
    // return all parking price zones
    @GetMapping("/parkingpricezones")
    public List<ParkingPriceZone> getAllParkingPriceZones() {
        return parkingPriceZoneRepository.findAll();
    }

    // return one parking price zone depending on (id) parameter
    @GetMapping("/parkingpricezones/{id}")
    public ParkingPriceZone getParkingPriceZone(@PathVariable Long id) {
        return parkingPriceZoneRepository.findById(id).get();
    }

    // POST POST POST POST
    // add a new parking price zone, (id) generated automatically
    @PostMapping("/parkingpricezones")
    public ResponseEntity<Object> addParkingPriceZone(@RequestBody ParkingPriceZone parkingPriceZone) {
        ParkingPriceZone savedParkingPriceZone = parkingPriceZoneRepository.save(parkingPriceZone);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedParkingPriceZone.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/parkingpricezones/active")     // filters out parking lots that are started 
    public List<ParkingPriceZone> getAllActiveParkingPriceZones() {
        return parkingPriceZoneRepository.findByStatus("active");
    }

    // PUT PUT PUT PUT
    // update a parking price zone
    @PutMapping("/parkingpricezones/{id}")
    public ResponseEntity<Object> updateParkingPriceZone(@RequestBody ParkingPriceZone parkingPriceZone,
            @PathVariable Long id) {
        Optional<ParkingPriceZone> parkingPriceZoneOptional = parkingPriceZoneRepository.findById(id);

        if (!parkingPriceZoneOptional.isPresent())
            return ResponseEntity.notFound().build();

        parkingPriceZone.setId(id);

        parkingPriceZoneRepository.save(parkingPriceZone);

        return ResponseEntity.noContent().build();
    }
}







