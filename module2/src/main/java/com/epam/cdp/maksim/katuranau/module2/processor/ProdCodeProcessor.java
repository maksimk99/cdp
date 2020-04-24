package com.epam.cdp.maksim.katuranau.module2.processor;

import com.epam.cdp.maksim.katuranau.module2.annotation.ProdCode;

import java.lang.reflect.Method;

public class ProdCodeProcessor {

    public static <T> void process(Class<T> clazz) throws Exception {
        T object = clazz.getConstructor().newInstance();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ProdCode.class)) {
                method.invoke(object);
            }
        }
    }
}
