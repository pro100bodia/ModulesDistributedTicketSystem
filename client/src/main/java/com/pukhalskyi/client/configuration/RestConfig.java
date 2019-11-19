package com.pukhalskyi.client.configuration;

import com.pukhalskyi.client.TicketSystemClient;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@EnableAutoConfiguration
@Configuration
public class RestConfig {
    @Bean
    public TicketSystemClient ticketSystemClient() {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .basicAuthentication("", "")
                .build();

        return new TicketSystemClient(restTemplate);
    }
}
