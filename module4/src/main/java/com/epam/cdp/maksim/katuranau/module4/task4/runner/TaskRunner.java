package com.epam.cdp.maksim.katuranau.module4.task4.runner;

import com.epam.cdp.maksim.katuranau.module4.task4.classloader.CustomClassLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskRunner {

    private static final Logger LOGGER = LogManager.getLogger();

    private final static List<String> TRANSPORT_CLASSPATH
            = Arrays.asList("com.epam.cdp.maksim.katuranau.module4.task4.bean.Cat",
            "com.epam.cdp.maksim.katuranau.module4.task4.bean.Dog");

    public static void main(String[] args) {
        LOGGER.info("Application started!");

        ClassLoader parentClassLoader = TaskRunner.class.getClassLoader();
        ClassLoader classLoader = new CustomClassLoader(parentClassLoader);
        List<Class<?>> pets = new ArrayList<>();

        LOGGER.info("CustomLoader was created.");
        loadClasses(classLoader, pets);

        invokePetMethods(pets);
    }

    private static void loadClasses(ClassLoader classLoader, List<Class<?>> pets) {
        for (String path : TaskRunner.TRANSPORT_CLASSPATH) {
            try {
                pets.add(classLoader.loadClass(path));
                LOGGER.info(path + " class was loaded to MetaSpace");
            } catch (ClassNotFoundException e) {
                LOGGER.info("Class " + path + "was not found");
            }
        }
    }

    private static void invokePetMethods(List<Class<?>> pets) {
        for (Class<?> clazz : pets) {
            try {
                Object ani = clazz.getConstructor().newInstance();
                clazz.getMethod("play").invoke(ani);
                clazz.getMethod("voice").invoke(ani);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException
                    | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
