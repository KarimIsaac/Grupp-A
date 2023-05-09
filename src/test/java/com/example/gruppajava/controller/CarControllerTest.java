package com.example.gruppajava.controller;

import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.gruppajava.entity.Car;

import com.example.gruppajava.repository.CarRepository;
import com.example.gruppajava.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

@WebMvcTest(CarController.class)
@AutoConfigureMockMvc(addFilters = false) // FALSE == SECURITY TURNED OFF
public class CarControllerTest {

    @MockBean
    CarRepository carRepository;

    @MockBean
    UserRepository userRepository;

    @Autowired
    private MockMvc mvc;

    Car myCar = new Car("UKR082");

    @Test
    void getCarShouldReturn200OK() throws Exception {
        mvc.perform(get("/api/cars"))
                .andExpect(status().isOk());
    }

    @Test
    void getCarShouldGiveListOfCars() throws Exception {
        mvc.perform(get("/api/cars"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    void getCarWithIdShouldGiveSingleCar() throws Exception {
        Mockito.when(carRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(myCar));

        mvc.perform(get("/api/cars/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(myCar)));
    }

    @Test
    void postCarShouldReturn201CreatedAndResponseEntity() throws Exception {

        String json = """
                    {
                        "licensePlateNr": "%s",
                        "userId": %d
                    }
                """;

        mvc.perform(
                MockMvcRequestBuilders
                        .post("/api/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format(json, "ukr123", 1))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.licensePlateNr")
                                .exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}