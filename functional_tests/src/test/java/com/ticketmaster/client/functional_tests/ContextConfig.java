package com.ticketmaster.client.functional_tests;

import com.ticketmaster.Application;
import com.ticketmaster.client.configuration.RestConfig;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = {Application.class, RestConfig.class}, loader = SpringBootContextLoader.class)
public class ContextConfig {
}
