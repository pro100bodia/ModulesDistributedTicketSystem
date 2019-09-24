package com.ticketmaster.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TicketModel {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;

    private Set<UserModel> users;
}
