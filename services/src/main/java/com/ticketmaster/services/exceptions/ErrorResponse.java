package com.ticketmaster.services.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
//@AllArgsConstructor
@EqualsAndHashCode(exclude = "timeStamp")
public class ErrorResponse {
    private int status;
    private String message;
    private Long timeStamp;

    public ErrorResponse(int status, String message, Long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
