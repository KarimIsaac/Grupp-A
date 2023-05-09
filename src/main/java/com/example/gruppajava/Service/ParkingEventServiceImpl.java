package com.example.gruppajava.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.gruppajava.entity.ParkingEvent;
import com.example.gruppajava.repository.CarRepository;
import com.example.gruppajava.repository.ParkSlotRepository;
import com.example.gruppajava.repository.ParkingEventRepository;
import com.example.gruppajava.repository.UserRepository;

@Service
public class ParkingEventServiceImpl implements ParkingEventService {
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final ParkSlotRepository parkSlotRepository;
    private final ParkingEventRepository parkingEventRepository;

    public ParkingEventServiceImpl(
        UserRepository userRepository,
        CarRepository carRepository,
        ParkSlotRepository parkSlotRepository,
        ParkingEventRepository parkingEventRepository
    ) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.parkSlotRepository = parkSlotRepository;
        this.parkingEventRepository = parkingEventRepository;
    }

    @Override
    public ParkingEvent addParkingEvent(Map<String, String> body) {
        Long userId = Long.parseLong(body.get("userId"));
        Long carId = Long.parseLong(body.get("carId"));
        Long parkingslotId = Long.parseLong(body.get("parkingslotId"));

        ParkingEvent parkingEvent = new ParkingEvent();
        parkingEvent.setUser(userRepository.findById(userId).get());
        parkingEvent.setCar(carRepository.findById(carId).get());
        parkingEvent.setParkSlot(parkSlotRepository.findById(parkingslotId).get());
        parkingEvent.setStartTime(LocalDateTime.now());
        parkingEvent.setEndTime(LocalDateTime.now().plusMinutes(10));
        parkingEvent.setActive(true);
        parkingEvent.setPaid(false);
        parkingEventRepository.save(parkingEvent);
        return parkingEvent;
    }

    @Override
    public ParkingEvent stopParkingEvent(Long id) {
        Optional<ParkingEvent> optionalParkingEvent = parkingEventRepository.findById(id);
        if (!optionalParkingEvent.isPresent()) {
            throw new RuntimeException("ParkingEvent not founnd");
        }
        ParkingEvent parkingEvent = optionalParkingEvent.get();
        LocalDateTime startTime = parkingEvent.getStartTime();
        LocalDateTime endTime = LocalDateTime.now();
        long duration = Duration.between(startTime, endTime).toMinutes();
        double price = duration * 0.17; // 0.17 kr/min ca. 10 kr/h ...
        price = Math.round(price * 100);
        parkingEvent.setPrice(price / 100); // 2 decimals.
        parkingEvent.setEndTime(endTime);
        parkingEvent.setActive(false);
        parkingEventRepository.save(parkingEvent);
        return parkingEvent;
    }
}
