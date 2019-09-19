package com.ticketmaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class TicketSystemClient {

    private RestTemplate restTemplate;

    @Autowired
    public TicketSystemClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto exchange(HttpHeaders httpHeaders, UserDto body, String returnedUrl){
        HttpEntity<UserDto> entity = new HttpEntity<>(body, httpHeaders);
        return restTemplate.exchange(returnedUrl, HttpMethod.GET, entity, UserDto.class).getBody();
    }
}
