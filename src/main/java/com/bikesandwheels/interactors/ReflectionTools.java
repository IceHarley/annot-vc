package com.bikesandwheels.interactors;

import com.google.common.base.Predicate;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.URL;
import java.util.Set;

public interface ReflectionTools {
    void setUrl(URL url);
    Set<Class<?>> getTypesAnnotatedWith(final Class<? extends Annotation> annotation);
    Set<Method> getMethodsAnnotatedWith(final Class<? extends Annotation> annotation);
    Set<Method> getMethods(Class<?> aClass);
    <T> Set<Class<? extends T>> getSubTypesOf(final Class<T> type);
    <T extends AnnotatedElement> Set<Annotation> getAnnotations(T type, Predicate<Annotation>... predicates);
}
