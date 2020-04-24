package com.epam.cdp.maksim.katuranau.module6.task1.bean;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class InformationReplacer implements MethodReplacer {
    @Override
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
        return "The string is generated in replaced method of InformationReplacer class";
    }
}
