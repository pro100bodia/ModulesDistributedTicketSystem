package com.ticketmaster.functional_tests;

import com.ticketmaster.TicketSystemClient;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;

import static org.junit.Assert.assertEquals;

public class StepDefs extends ItApplication {
    private int actualCode;

    @Autowired
    private TicketSystemClient ticketSystemClient;

    @When("I try to access all users")
    public void try_to_access_all_users() {
        try {
            ResponseEntity responseEntity = ticketSystemClient.getAllUsers();
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
