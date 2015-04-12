package com.bikesandwheels.interactors.revised_classes_searcher;

import com.bikesandwheels.annotations.*;
import com.google.common.collect.Sets;
import org.reflections.*;
import org.reflections.util.ClasspathHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.*;
import java.util.*;

public class PathRevisedClassesSearcher implements RevisedClassesSearcher {
    private final Reflections reflections;

    public PathRevisedClassesSearcher(Reflections reflections) {
        this.reflections = reflections;
    }

    @SuppressWarnings("unchecked")
    public Set<Class<?>> search() {
        Set<Class<?>> annotatedTypes = getTypesAnnotatedWith(Revision.class, History.class);
        annotatedTypes.addAll(getDeclaringClasses(getMethodsAnnotatedWith(Revision.class, History.class)));
        return annotatedTypes;
    }

    private Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation>... annotations) {
        Set<Class<?>> annotatedTypes = Sets.newHashSet();
        for (Class<? extends Annotation> annotation : annotations)
            annotatedTypes.addAll(reflections.getTypesAnnotatedWith(annotation));
        return annotatedTypes;
    }

    private Set<Method> getMethodsAnnotatedWith(Class<? extends Annotation>... annotations) {
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
