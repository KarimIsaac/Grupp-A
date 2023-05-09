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

import com.example.gruppajava.entity.ParkSlot;
import com.example.gruppajava.repository.ParkSlotRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

@WebMvcTest(ParkSlotController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ParkSlotControllerTest {
  
  @MockBean
  ParkSlotRepository parkslotRepository;

//  @MockBean
//  ParkPriceZoneRepository parkpricezoneRepository;

  @Autowired
  private MockMvc mvc;

  @Test
  void getParkSlotShouldReturn200OK() throws Exception {
      mvc.perform(get("/api/parkslots"))
        .andExpect(status().isOk());
  }

  @Test
  void getParkSlotShouldGiveListOfParkSlots() throws Exception{
    mvc.perform(get("/api/parkslots"))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(content().json("[]"));
  }

  ParkSlot aParkSlot = new ParkSlot(55300, true);

  @Test
  void getParkSlotWithIdShouldGiveSingleParkSlot() throws Exception{
    Mockito.when(parkslotRepository.findById(ArgumentMatchers.any()))
      .thenReturn(Optional.of(aParkSlot));
    mvc.perform(get("/api/parkslots/1"))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(content().json(asJsonString(aParkSlot)));
  }

  @Test
  void postParkSlotShouldReturn201CreatedAndResponseEntity() throws Exception{
    Mockito.when(parkslotRepository.save(ArgumentMatchers.any())).thenReturn(aParkSlot);

    mvc.perform(MockMvcRequestBuilders
      .post("/api/parkslots")
      .content(asJsonString(aParkSlot))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated())
      .andExpect(MockMvcResultMatchers.jsonPath("$.zone_id").exists());
  }

  public static String asJsonString(final Object obj) {
    try {
        return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
  }
  
}

