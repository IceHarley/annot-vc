package com.bikesandwheels.interactors.annotated_classes_searcher.scanners;

import com.bikesandwheels.interactors.ReflectionTools;
import com.bikesandwheels.interactors.annotated_classes_searcher.AnnotatedScanner;
import com.google.common.collect.Sets;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

public class MethodsAnnotatedScanner implements AnnotatedScanner {
    private Class<? extends Annotation>[] annotations;
    private ReflectionTools reflectionTools;

    public MethodsAnnotatedScanner(Class<? extends Annotation>... annotations) {
        this.annotations = annotations;
    }

    public void setReflectionTools(ReflectionTools reflectionTools) {
        this.reflectionTools = reflectionTools;
    }

    public Set<Class<?>> scan() {
        return getDeclaringClasses(getAnnotatedMethods());
    }

    private Set<Method> getAnnotatedMethods() {
        Set<Method> annotatedMethods = Sets.newHashSet();
        for (Object annotation : annotations)
            annotatedMethods.addAll(reflectionTools.getMethodsAnnotatedWith((Class<? extends Annotation>) annotation));
        return annotatedMethods;
    }

    private Set<Class<?>> getDeclaringClasses(Set<Method> annotatedMethods) {
        Set<Class<?>> declaredClasses = Sets.newHashSet();
        for (Method method : annotatedMethods)
            declaredClasses.add(method.getDeclaringClass());
        return declaredClasses;
    }
}
