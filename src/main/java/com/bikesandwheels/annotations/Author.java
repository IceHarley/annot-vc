package com.bikesandwheels.annotations;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface Author {
    String DEFAULT_AUTHOR = "Unknown";

    String value() default DEFAULT_AUTHOR;
}
