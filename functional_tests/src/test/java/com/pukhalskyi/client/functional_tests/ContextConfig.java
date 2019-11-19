package com.pukhalskyi.client.functional_tests;

import com.pukhalskyi.Application;
import com.pukhalskyi.client.configuration.RestConfig;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = {Application.class, RestConfig.class}, loader = SpringBootContextLoader.class)
public class ContextConfig {
}
