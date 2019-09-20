package com.ticketmaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class TicketSystemClient {

    private RestTemplate restTemplate;

    @Autowired
    public TicketSystemClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<UserDto> getUser(String username){
        String template = "http://localhost:8080/api/users/{username}";
        UriTemplate uriTemplate = new UriTemplate(template);
        Map<String, String> params = new HashMap<>();
        params.put("username", username);

        UriBuilder builder = UriComponentsBuilder.fromPath(template);
        URI uri = builder.build(params);

        return restTemplate.getForEntity(uri, UserDto.class);
    }
}
