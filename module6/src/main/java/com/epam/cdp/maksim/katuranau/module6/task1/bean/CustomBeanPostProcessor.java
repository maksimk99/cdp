package com.epam.cdp.maksim.katuranau.module6.task1.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        if (o instanceof BeanF) {
            System.out.println("Bean " + s + " postProcessBeforeInitialization phase");
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        if (o instanceof BeanF) {
            System.out.println("Bean " + s + " postProcessAfterInitialization phase");
        }
        return o;
    }
}
