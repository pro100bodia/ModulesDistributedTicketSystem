package com.pukhalskyi.controller;

import com.google.gson.Gson;
import com.pukhalskyi.Application;
import com.pukhalskyi.api.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class UserControllerItTest {

    @Autowired
    private MockMvc mvcMock;


    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    public void givenAllUsersRequest_whenFindAll_thenStatus200() throws Exception {
        mvcMock.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    public void givenUsername_whenGetUserByUsername_thenStatus200() throws Exception {
        mvcMock.perform(get("/api/users/{username}", "serhiilytka")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("serhiilytka"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    public void givenUserDto_whenSaveUser_thenStatus200() throws Exception {
        UserDto userDto = new UserDto(4L, "bohdanpukhalskyi", "1111", "Bohdan", "Pukhalskyi", "bohdanpukhalskyi@gmail.com", null);

        Gson gson = new Gson();

        String requestBody = gson.toJson(userDto);

        mvcMock.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    public void givenUserDto_whenUpdateUser_thenStatus200() throws Exception {
        UserDto userDto = new UserDto(4L, "bohdanpukhalskyi", "1111", "Bohdan", "Pukhalskyi", "bohdanpukhalskyi@gmail.com", null);

        Gson gson = new Gson();

        String requestBody = gson.toJson(userDto);

        mvcMock.perform(MockMvcRequestBuilders.put("/api/users/{username}", "bohdanpukhalskyi")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    public void givenUsername_whenDeleteUser_thenStatus200() throws Exception {
        mvcMock.perform(delete("/api/users/{username}", "serhiilytka")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
