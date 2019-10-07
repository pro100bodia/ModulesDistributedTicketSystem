package com.ticketmaster.logging.aspect;

import org.aspectj.lang.JoinPoint;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LogBeforeAnnotationAspectTest {
    @Mock
    private JoinPoint JoinPoint;

    private LogBeforeAnnotationAspect aspect = new LogBeforeAnnotationAspect();

    @Ignore
    @Test
    public void shouldIntercept() throws Throwable {
        aspect.logBefore();

        verify(JoinPoint, times(1)).getSignature();
    }
}