package com.epam.cdp.maksim.katuranau.module2.processor;

import com.epam.cdp.maksim.katuranau.module2.annotation.ThisCodeSmell;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class ThisCodeSmellsProcessor {

    public static void process(Class clazz) {
        ThisCodeSmell[] listAnnotations;
        if (Objects.nonNull(listAnnotations = checkAnnotation(clazz))) {
            System.out.println("Class name: " + clazz.getSimpleName() + ", Authority of persons: "
                    + Arrays.stream(listAnnotations).map(ThisCodeSmell::reviewer).collect(Collectors.toList())
                    + ", number of annotations: " + listAnnotations.length);
        }
        for (Method method : clazz.getDeclaredMethods()) {
            ThisCodeSmell[] listAnnotationsOnMethod;
            if (Objects.nonNull(listAnnotationsOnMethod = checkAnnotation(method))) {
                System.out.println("Method: " + method.toString() + ", Authority of persons: "
                        + Arrays.stream(listAnnotationsOnMethod)
                        .map(ThisCodeSmell::reviewer).collect(Collectors.toList())
                        + ", number of annotations: " + listAnnotationsOnMethod.length);
            }
        }
        for (Field field : clazz.getDeclaredFields()) {
            ThisCodeSmell[] listAnnotationsOnField;
            if (Objects.nonNull(listAnnotationsOnField = checkAnnotation(field))) {
                System.out.println("Class name: " + clazz.getSimpleName() + ", field: " + field.getName()
                        + ", Authority of persons: " + Arrays.stream(listAnnotationsOnField)
                        .map(ThisCodeSmell::reviewer).collect(Collectors.toList())
                        + ", number of annotations: " + listAnnotationsOnField.length);
            }
        }
    }

    private static ThisCodeSmell[] checkAnnotation(AnnotatedElement accessibleObject) {
        if (accessibleObject.isAnnotationPresent(ThisCodeSmell.ThisCodeSmells.class)
                || accessibleObject.isAnnotationPresent(ThisCodeSmell.class)) {
            return accessibleObject.getAnnotationsByType(ThisCodeSmell.class);
        } else {
            return null;
        }
    }
}
