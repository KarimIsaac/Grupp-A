package com.example.gruppajava;

import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.gruppajava.Service.ParkingEventService;
import com.example.gruppajava.controller.ParkingEventController;
import com.example.gruppajava.entity.ParkingEvent;

@WebMvcTest(ParkingEventController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false) // FALSE == SECURITY TURNED OFF
public class ParkingEventControllerTest {

    @MockBean
    ParkingEventController parkingEventController;

    @Autowired
    private MockMvc mvc;
    
    @Mock
    private ParkingEventService parkingEventService;

    @Test
    void getParkingEventShouldReturn200OK() throws Exception {
        mvc.perform(get("/api/parkingevents"))
                .andExpect(status().isOk());
    }

    @Test
    void getParkingEventByIdShouldReturn200OK() throws Exception {
        mvc.perform(get("/api/parkingevents/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getParkingEventShouldGiveListOfParkingEvents() throws Exception {
        mvc.perform(get("/api/parkingevents"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    public void testAddParkingEvent() throws Exception {
        when(parkingEventService.addParkingEvent(anyMap())).thenReturn(new ParkingEvent());
        ParkingEventController parkingEventController = new ParkingEventController(parkingEventService);
        MockMvc mvc = MockMvcBuilders.standaloneSetup(parkingEventController).build();

        mvc.perform(
            post("/api/parkingevents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\"userId\": 1, \"carId\": 2, \"parkingslotId\": 3}"
                )
        )
            .andExpect(status().isCreated())
            .andExpect(content().json("{}"));
    }
}
