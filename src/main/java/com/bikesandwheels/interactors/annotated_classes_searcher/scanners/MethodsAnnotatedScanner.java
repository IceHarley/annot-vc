package com.bikesandwheels.interactors.annotated_classes_searcher.scanners;

import com.bikesandwheels.interactors.ReflectionTools;
import com.bikesandwheels.interactors.annotated_classes_searcher.AnnotatedScanner;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

@Component
public class MethodsAnnotatedScanner implements AnnotatedScanner {
    @Autowired
    private Class<? extends Annotation>[] annotations;
    @Autowired
    private ReflectionTools reflectionTools;

    public Set<Class<?>> scan() {
        return getDeclaringClasses(getAnnotatedMethods());
    }

    @SuppressWarnings({"unchecked"})
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
