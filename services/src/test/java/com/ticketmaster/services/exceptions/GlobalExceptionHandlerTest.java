package com.ticketmaster.services.exceptions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionHandlerTest {
    private GlobalExceptionHandler subject;
    private ResponseEntity<ErrorResponse> result;

    private Exception exception;

    @Mock
    private WebRequest request;

    @Before
    public void setUp() {
        subject = new GlobalExceptionHandler();
    }

    @Test
    public void shouldHandleNotFoundException() {
        //given
        exception = new NotFoundException("User not found, 404");
        //when
        result = subject.handleException(exception);
        //then
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse response = new ErrorResponse(
                status.value(),
                exception.getMessage() + ", " + status,
                System.currentTimeMillis()
        );

        assertEquals(new ResponseEntity<>(response, status), result);
    }
}