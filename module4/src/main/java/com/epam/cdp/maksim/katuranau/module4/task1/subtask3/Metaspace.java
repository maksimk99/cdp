package com.epam.cdp.maksim.katuranau.module4.task1.subtask3;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Metaspace {
    /*
    -XX:MaxMetaspaceSize=300m
     */
    public static void main(String[] args)
            throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException {
        List<Object> list = new ArrayList<>();
        URL[] url = new URL[]{
                Thread.currentThread().getContextClassLoader().getResource(".")
        };
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            ClassLoader classLoader = new CustomClassLoader(url);
            Class<?> clazz = classLoader.loadClass("com.epam.cdp.maksim.katuranau.module4.task1.subtask3.Metaspace");
            list.add(clazz.getConstructor().newInstance());
        }
    }
}
