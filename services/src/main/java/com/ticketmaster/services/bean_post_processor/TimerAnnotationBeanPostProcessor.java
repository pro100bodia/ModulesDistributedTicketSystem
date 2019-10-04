package com.ticketmaster.services.bean_post_processor;

import com.ticketmaster.services.anotatoin.Timer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

@Component
public class TimerAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;

    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = bean.getClass();

        if (beanClass.isAnnotationPresent(Timer.class)) {
            System.out.println("------------------------- " + beanClass);
            Enhancer e = new Enhancer();
            e.setSuperclass(beanClass);
            e.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
                System.out.println("Засекаю");
                long start = System.nanoTime();

                Object retObj = methodProxy.invoke(bean, objects);

                System.out.println((System.nanoTime() - start));
                System.out.println("Усё");
                return retObj;
            });

            Constructor constructor = beanClass.getConstructors()[0];
            return e.create(constructor.getParameterTypes(), constructor.getParameters());
        }

        return bean;
    }
}

class MyInvocationHandler implements MethodInterceptor {
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("Засекаю");
        long start = System.nanoTime();

        Object retObj = proxy.invoke(obj, args);

        System.out.println((System.nanoTime() - start));
        System.out.println("Усё");
        return retObj;
    }
}
