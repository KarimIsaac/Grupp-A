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

import com.example.gruppajava.entity.User;
import com.example.gruppajava.repository.CarRepository;
import com.example.gruppajava.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false) // FALSE == SECURITY TURNED OFF
public class UserControllerTest {

    @MockBean
    UserRepository userRepository;
    @MockBean
    CarRepository carRepository;
    @Autowired
    private MockMvc mvc;

    User myUser = new User("Bob", "Sideshow");

    @Test
    void getUserShouldReturn200OK() throws Exception {
        mvc.perform(get("/api/users"))
                .andExpect(status().isOk());
    }

    @Test
    void getUserShouldGiveListOfUsers() throws Exception {
        mvc.perform(get("/api/users"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    void getUserWithIdShouldGiveSingleUser() throws Exception {
        Mockito.when(userRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(myUser));

        mvc.perform(get("/api/users/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(myUser)));
    }

    @Test
    void postUserShouldReturn201CreatedAndResponseEntity() throws Exception {
        Mockito.when(userRepository.save(ArgumentMatchers.any())).thenReturn(myUser);

        mvc.perform(MockMvcRequestBuilders
                .post("/api/users")
                .content(asJsonString(myUser))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
