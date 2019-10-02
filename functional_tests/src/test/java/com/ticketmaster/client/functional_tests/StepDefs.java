package com.ticketmaster.client.functional_tests;

import com.ticketmaster.api.dto.UserDto;
import com.ticketmaster.client.TicketSystemClient;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class StepDefs extends ContextConfig {
    private List<UserDto> response;

    @Autowired
    private TicketSystemClient ticketSystemClient;

    @When("I try to access all users")
    public void try_to_access_all_users() {
        response = ticketSystemClient.getAllUsers();
    }

    @Then("Receive response with code of {int}")
    public void receiveResponseWithCodeOf(int arg0) {
        assertNotNull(response);
    }
}