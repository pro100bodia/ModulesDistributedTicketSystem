package com.ticketmaster;

import com.ticketmaster.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketSystemClient {
    @Autowired
    private RestTemplate restTemplate;

    public TicketSystemClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    ResponseEntity<List<UserDto>> getAllUsers() {
        String template = "http://localhost:8080/api/users";
        UriBuilder builder = UriComponentsBuilder.fromPath(template);
        URI uri = builder.build();

        return restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<UserDto>>() {
        });
    }

    ResponseEntity<UserDto> getUser(String username) {
        String template = "http://localhost:8080/api/users/{username}";
        Map<String, String> params = new HashMap<>();
        params.put("username", username);

        UriBuilder builder = UriComponentsBuilder.fromPath(template);
        URI uri = builder.build(params);

        return restTemplate.getForEntity(uri, UserDto.class);
    }

    public ResponseEntity<UserDto> addUser(UserDto userDto) {
        String template = "http://localhost:8080/api/users/";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(userDto, httpHeaders);

        return restTemplate.exchange(template, HttpMethod.POST, httpEntity, UserDto.class);
    }

    public ResponseEntity<UserDto> updateUser(Long id, UserDto userDto) {
        String template = "http://localhost:8080/api/users/" + id;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(userDto, httpHeaders);

        return restTemplate.exchange(template, HttpMethod.PUT, httpEntity, UserDto.class);
    }

    public ResponseEntity<UserDto> deleteUser(Long id) {
        String template = "http://localhost:8080/api/users/" + id;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(template, HttpMethod.DELETE, httpEntity, UserDto.class);
    }
}
