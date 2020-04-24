package com.epam.cdp.maksim.katuranau.module2.annotation;

import java.lang.annotation.*;

@Repeatable(ThisCodeSmell.ThisCodeSmells.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ThisCodeSmell {
    String reviewer();

    @Retention(RetentionPolicy.RUNTIME)
    @interface ThisCodeSmells {
        ThisCodeSmell[] value();
    }
}
