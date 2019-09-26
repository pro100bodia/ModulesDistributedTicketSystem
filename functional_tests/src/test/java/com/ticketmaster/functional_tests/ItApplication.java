package com.ticketmaster.functional_tests;

import com.ticketmaster.services.Application;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class, loader = SpringBootContextLoader.class)
public class ItApplication {

    @LocalServerPort
    protected int port;
}
