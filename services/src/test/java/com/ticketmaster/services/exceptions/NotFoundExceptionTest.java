package com.ticketmaster.services.exceptions;

import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class NotFoundExceptionTest {
    @Test
    public void shouldPassAllPojoTests() {
        //given
        final Class<?> classUnderTest = NotFoundException.class;

        //when

        //then
        assertPojoMethodsFor(classUnderTest).testing(Method.CONSTRUCTOR).areWellImplemented();
    }
}