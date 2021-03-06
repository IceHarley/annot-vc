package com.bikesandwheels.annotations;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RUNTIME)
public @interface Revision {
    String DEFAULT_COMMENT = "";

    Date date();
    Author[] authors() default @Author;
    String comment() default DEFAULT_COMMENT;
}
