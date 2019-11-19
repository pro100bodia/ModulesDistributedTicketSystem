package com.pukhalskyi.client;

import com.pukhalskyi.api.dto.UserDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

public class TicketSystemClient {
    private static final String TEMPLATE = "http://localhost:8080/api/users/";
    private RestTemplate restTemplate;

    public TicketSystemClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<UserDto> getAllUsers() {
        return restTemplate.exchange(TEMPLATE, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<UserDto>>() {
                }).getBody();
    }

    public UserDto getUser(String username) {
        return restTemplate.getForEntity(TEMPLATE + username, UserDto.class).getBody();
    }

    public UserDto addUser(UserDto userDto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(userDto, httpHeaders);

        return restTemplate.exchange(TEMPLATE, HttpMethod.POST, httpEntity, UserDto.class).getBody();
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(userDto, httpHeaders);

        return restTemplate.exchange(TEMPLATE + id, HttpMethod.PUT, httpEntity, UserDto.class).getBody();
    }

    public UserDto deleteUser(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(TEMPLATE + id, HttpMethod.DELETE, httpEntity, UserDto.class).getBody();
    }
}
