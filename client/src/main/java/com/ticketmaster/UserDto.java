package com.ticketmaster;

import java.util.Set;

public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    private Set<TicketDto> tickets;

    public UserDto() {
    }

    public UserDto(Long id, String username, String firstName, String lastName, String email, Set<TicketDto> tickets) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.tickets = tickets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<TicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketDto> tickets) {
        this.tickets = tickets;
    }
}
