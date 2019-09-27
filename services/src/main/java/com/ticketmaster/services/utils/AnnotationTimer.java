package com.ticketmaster.services.utils;

import com.ticketmaster.services.annotation.Timer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class AnnotationTimer implements BeanPostProcessor {
    Long start;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getFields();
        for (Field field : fields) {
            Timer timerAnno = field.getAnnotation(Timer.class);
            if (timerAnno != null) {
                start = System.currentTimeMillis();
            }
        }

        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (start != null)
            System.out.println("******************************Execution time: " + (System.currentTimeMillis() - start));
        return null;
    }
}
