package com.ticketmaster.services.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private Long timeStamp;
}
