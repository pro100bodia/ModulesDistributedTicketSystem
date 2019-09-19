package com.ticketmaster.configuration;

import com.ticketmaster.TicketSystemClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAutoConfiguration
public class RestConfig {
    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public TicketSystemClient restTemplate() {
        return new TicketSystemClient(restTemplate);
    }
}
