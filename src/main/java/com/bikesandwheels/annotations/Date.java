package com.bikesandwheels.annotations;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface Date {
    int year();
    int month();
    int day();
}
