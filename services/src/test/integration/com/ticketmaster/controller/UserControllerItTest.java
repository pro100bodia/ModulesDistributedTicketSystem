package com.ticketmaster.controller;

import com.google.gson.Gson;
import com.ticketmaster.Application;
import com.ticketmaster.api.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class UserControllerItTest {

    @Autowired
    private MockMvc mvcMock;

    @Test
    public void givenAllUsersReques_whenFindAll_thenStatus200() throws Exception {
        mvcMock.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void givenUsername_whenGetUserByUsername_thenStatus200() throws Exception {
        mvcMock.perform(get("/api/users/{username}", "serhiilytka")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("serhiilytka"));
    }

    @Test
    public void givenUserDto_whenSaveUser_thenStatus200() throws Exception {
        UserDto userDto = new UserDto(4L, "bohdanpukhalskyi", "Bohdan", "Pukhalskyi", "bohdanpukhalskyi@gmail.com", null);

        Gson gson = new Gson();

        String requestBody = gson.toJson(userDto);

        mvcMock.perform(post("/api/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                .andExpect(status().isOk());
    }
}
