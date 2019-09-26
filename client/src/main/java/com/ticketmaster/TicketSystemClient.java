package com.ticketmaster;

import com.ticketmaster.api.dto.UserDto;
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
    private static final String template = "http://localhost:8080/api/users/";
    private RestTemplate restTemplate;

    public TicketSystemClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<List<UserDto>> getAllUsers() {
        return restTemplate.exchange(template, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<UserDto>>() {
                });
    }

    public ResponseEntity<UserDto> getUser(String username) {
        Map<String, String> params = new HashMap<>();
        params.put("username", username);

        UriBuilder builder = UriComponentsBuilder.fromPath(template + "{username}");
        URI uri = builder.build(params);

        return restTemplate.getForEntity(uri, UserDto.class);
    }

    public ResponseEntity<UserDto> addUser(UserDto userDto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(userDto, httpHeaders);

        return restTemplate.exchange(template, HttpMethod.POST, httpEntity, UserDto.class);
    }

    public ResponseEntity<UserDto> updateUser(Long id, UserDto userDto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(userDto, httpHeaders);

        return restTemplate.exchange(template + id, HttpMethod.PUT, httpEntity, UserDto.class);
    }

    public ResponseEntity<UserDto> deleteUser(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(template + id, HttpMethod.DELETE, httpEntity, UserDto.class);
    }
}
