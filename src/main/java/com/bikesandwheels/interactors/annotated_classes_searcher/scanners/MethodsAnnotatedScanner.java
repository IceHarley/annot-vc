package com.bikesandwheels.interactors.annotated_classes_searcher.scanners;

import com.bikesandwheels.interactors.annotated_classes_searcher.AnnotatedScanner;
import com.google.common.collect.Sets;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

public class MethodsAnnotatedScanner implements AnnotatedScanner {
    private Class<? extends Annotation>[] annotations;
    private Reflections reflections;

    public MethodsAnnotatedScanner(Class<? extends Annotation>... annotations) {
        this.annotations = annotations;
    }

    public void setReflections(Reflections reflections) {
        this.reflections = reflections;
    }

    public Set<Class<?>> scan() {
        return getDeclaringClasses(getAnnotatedMethods());
    }

    private Set<Method> getAnnotatedMethods() {
        Set<Method> annotatedMethods = Sets.newHashSet();
        for (Class<? extends Annotation> annotation : annotations)
            annotatedMethods.addAll(reflections.getMethodsAnnotatedWith(annotation));
        return annotatedMethods;
    }

    private Set<Class<?>> getDeclaringClasses(Set<Method> annotatedMethods) {
        Set<Class<?>> declaredClasses = Sets.newHashSet();
        for (Method method : annotatedMethods)
            declaredClasses.add(method.getDeclaringClass());
        return declaredClasses;
    }
}
