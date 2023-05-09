package com.example.gruppajava.controller;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.gruppajava.Service.ParkingEventService;
import com.example.gruppajava.entity.ParkingEvent;
import com.example.gruppajava.repository.ParkingEventRepository;


@RestController
@RequestMapping("/api/")
public class ParkingEventController {
    @Autowired ParkingEventRepository parkingEventRepository;
    private final ParkingEventService parkingEventService;

    public ParkingEventController(ParkingEventService parkingEventService) {
        this.parkingEventService = parkingEventService;
    }

    // Get all parking events.
    @GetMapping("parkingevents")
    public Iterable<ParkingEvent> getAllParkingEvents() {
        return parkingEventRepository.findAll();
    }

    // Create a new parking event.
    @PostMapping("parkingevents")
    public ResponseEntity<ParkingEvent> addParkingEvent(@RequestBody Map<String, String> body) {
        ParkingEvent parkingEvent = parkingEventService.addParkingEvent(body);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(parkingEvent.getId())
            .toUri();
        return ResponseEntity.created(location).body(parkingEvent);
    }

    // Stop a parking event.
    @PutMapping("parkingevents/{id}")
    public ResponseEntity<ParkingEvent> stopParkingEvent(@PathVariable Long id) {
        ParkingEvent parkingEvent = parkingEventService.stopParkingEvent(id);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(parkingEvent.getId())
            .toUri();
        return ResponseEntity.created(location).body(parkingEvent);
    }

    // Get parking event by id.
    @GetMapping("parkingevents/{id}")
    public ParkingEvent getParkingEventById(@PathVariable Long id) {
        return parkingEventRepository.findById(id).get();
    }

    // Patch parking event by id. Adds x minutes to the parking event. example: (/api/parkingevents/{ID}?eventDurationMin={MINUTES})
    @PatchMapping("parkingevents/{id}")
    public ResponseEntity<ParkingEvent> patchParkingEventById(@PathVariable Long id, @RequestParam("eventDurationMin") int eventDurationMin) {
    ParkingEvent parkingEvent = parkingEventRepository.findById(id).get();
    parkingEvent.setEndTime(parkingEvent.getEndTime().plusMinutes(eventDurationMin));

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .queryParam("eventDurationMin", eventDurationMin)
        .buildAndExpand(parkingEvent.getId())
        .toUri();
    return ResponseEntity.created(location).body(parkingEvent);
}

    // Get parking event by user id.
    @GetMapping("parkingevents/user/{id}")
    public Iterable<ParkingEvent> getParkingEventByUserId(@PathVariable Long id) {
        return parkingEventRepository.findByUserId(id);
    }

    // Get parking event by car id.
    @GetMapping("parkingevents/car/{id}")
    public Iterable<ParkingEvent> getParkingEventByCarId(@PathVariable Long id) {
        return parkingEventRepository.findByCarId(id);
    }

    // Need more patch routes for updating parking events later...

}
