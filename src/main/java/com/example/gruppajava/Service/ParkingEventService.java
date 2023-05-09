package com.example.gruppajava.Service;

import java.util.Map;

import com.example.gruppajava.entity.ParkingEvent;

public interface ParkingEventService {
    ParkingEvent addParkingEvent(Map<String, String> body);
    ParkingEvent stopParkingEvent(Long id);
}
