package com.pukhalskyi.logging.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LogBeforeAnnotationAspectTest {
    @Mock
    private JoinPoint joinPoint;

    @Mock
    private Logger logger;

    private LogBeforeAnnotationAspect aspect = new LogBeforeAnnotationAspect();

    @Test
    public void shouldIntercept() {
//        Signature signature = mock(Signature.class);
//        when(joinPoint.getSignature()).thenReturn(signature);
//        when(signature.toLongString()).thenReturn("Test signature");
//        Object [] args = mock(Object[].class);
//        when(joinPoint.getArgs()).thenReturn(args);
//        when(Arrays.toString(args)).thenReturn("[test arg]");

        aspect.logBefore(joinPoint);

        verify(joinPoint, times(1)).getSignature();
//        verify(logger, times(1)).info(anyString());
    }
}