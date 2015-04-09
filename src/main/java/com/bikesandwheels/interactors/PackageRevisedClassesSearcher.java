package com.bikesandwheels.interactors;

import com.bikesandwheels.annotations.*;
import com.google.common.collect.Sets;
import org.reflections.*;
import org.reflections.scanners.*;
import org.reflections.util.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.*;
import java.util.*;

public class PackageRevisedClassesSearcher implements RevisedClassesSearcher {
    protected Reflections reflections;

    public PackageRevisedClassesSearcher(Class<?> baseClass) throws MalformedURLException {
        reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forClass(baseClass))
                .filterInputsBy(new FilterBuilder().include(getPackageFilter(baseClass)))
                .setScanners(
                        new SubTypesScanner(true),
                        new TypeAnnotationsScanner(),
                        new MethodAnnotationsScanner()));
    }

    private String getPackageFilter(Class<?> baseClass) {
        return String.format("%s\\$.*", baseClass.getName());
    }

    public Set<Class<?>> search() {
        Set<Class<?>> annotatedTypes = getTypesAnnotatedWith(Revision.class, History.class);
        Set<Method> annotatedMethods = getMethodsAnnotatedWith(Revision.class, History.class);
        annotatedTypes.addAll(getDeclaringClasses(annotatedMethods));
        return annotatedTypes;
    }

    protected Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation>... annotations) {
        Set<Class<?>> annotatedTypes = Sets.newHashSet();
        for (Class<? extends Annotation> annotation : annotations)
            annotatedTypes.addAll(reflections.getTypesAnnotatedWith(annotation));
        return annotatedTypes;
    }

    protected Set<Method> getMethodsAnnotatedWith(Class<? extends Annotation>... annotations) {
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
