package com.bikesandwheels.engine;

import com.bikesandwheels.annotations.Revision;
import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.*;

import java.lang.reflect.Method;
import java.net.*;
import java.util.*;

public class AnnotatedClassesSearcher {
    Reflections reflections;


    public AnnotatedClassesSearcher(Class<?> baseClass) throws MalformedURLException {
        reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forClass(baseClass))
                .filterInputsBy(new FilterBuilder().include(baseClass.getName() + "\\$.*"))
                .setScanners(
                        new SubTypesScanner(true),
                        new TypeAnnotationsScanner(),
                        new MethodAnnotationsScanner()));
    }

    public Set<Class<?>> getAnnotatedClasses() {
        Set<Class<?>> annotatedTypes = reflections.getTypesAnnotatedWith(Revision.class);
        Set<Method> annotatedMethods = reflections.getMethodsAnnotatedWith(Revision.class);
        for (Method method : annotatedMethods)
            annotatedTypes.add(method.getDeclaringClass());
        return annotatedTypes;
    }
}
