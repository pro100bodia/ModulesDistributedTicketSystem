package com.ticketmaster.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserModel {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    private Set<TicketModel> tickets;
}
