package com.example.gruppajava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gruppajava.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}