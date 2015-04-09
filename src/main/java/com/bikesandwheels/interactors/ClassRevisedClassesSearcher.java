package com.bikesandwheels.interactors;

import com.google.common.base.Predicate;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.util.Set;

import static com.google.common.collect.Sets.filter;

public class ClassRevisedClassesSearcher extends PackageRevisedClassesSearcher {
    private final Set<? extends Class<?>> allowedClasses;

    public ClassRevisedClassesSearcher(Class<?> baseClass) throws MalformedURLException {
        super(baseClass);
        allowedClasses = reflections.getSubTypesOf(baseClass.getClass());
    }

    @Override
    protected Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation>... annotations) {
        Set<Class<?>> typesAnnotatedWith = super.getTypesAnnotatedWith(annotations);
        return filter(typesAnnotatedWith, allowedOnly());
    }

    public Predicate<Class<?>> allowedOnly() {
        return new Predicate<Class<?>>() {
            public boolean apply(@Nullable Class<?> aClass) {
                return allowedClasses.contains(aClass);
            }
        };
    }
}
