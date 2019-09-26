package com.ticketmaster.functional_tests;

import com.ticketmaster.api.dto.UserDto;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

public class StepDefs extends ItApplication {
    private String role;
    private int actualCode;
    private ResponseEntity responseEntity;

    @When("I try to access all users")
    public void try_to_access_all_users() {
        try {
            responseEntity = new RestTemplate()
                    .exchange("http://localhost:" + port + "/api/users", HttpMethod.GET, null,
                            new ParameterizedTypeReference<UserDto>() {
                            });
            actualCode = responseEntity.getStatusCodeValue();
        } catch (HttpStatusCodeException e) {
            actualCode = e.getRawStatusCode();
        }
    }

    @Then("Receive response with code of {int}")
    public void receiveResponseWithCodeOf(int arg0) {
        assertEquals(arg0, actualCode);
    }
}
