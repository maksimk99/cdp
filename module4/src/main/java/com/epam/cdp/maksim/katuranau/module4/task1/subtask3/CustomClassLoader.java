package com.epam.cdp.maksim.katuranau.module4.task1.subtask3;

import java.net.URL;
import java.net.URLClassLoader;

public class CustomClassLoader extends URLClassLoader {
    public CustomClassLoader(URL[] urls) {
        super(urls);
    }

    @Override
    public Class<?> loadClass(String className) throws ClassNotFoundException {
        if (className.equals("com.epam.cdp.maksim.katuranau.module4.task1.subtask3.Metaspace")) {
            return findClass(className);
        } else {
            return super.loadClass(className);
        }
    }
}
