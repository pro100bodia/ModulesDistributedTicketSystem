package com.ticketmaster.exceptions;

import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class ErrorResponseTest {
    @Test
    public void shouldPassAllPojoTests() {
        //given
        final Class<?> classUnderTest = ErrorResponse.class;

        //when

        //then
        assertPojoMethodsFor(classUnderTest).testing(Method.CONSTRUCTOR).areWellImplemented();
    }
}