package com.bikesandwheels.interactors;

import com.google.common.base.Predicate;
import org.reflections.Reflections;

import javax.annotation.Nullable;
import java.net.MalformedURLException;
import java.util.Set;

import static com.google.common.collect.Sets.filter;

public class ClassRevisedClassesSearcher implements RevisedClassesSearcher {
    private final Class<?> baseClass;
    private PackageRevisedClassesSearcher packageSearcher;
    private Set<? extends Class<?>> allowedClasses;

    public ClassRevisedClassesSearcher(Class<?> baseClass) throws MalformedURLException {
        this.baseClass = baseClass;
        packageSearcher = new PackageRevisedClassesSearcher(baseClass);
    }

    public Set<Class<?>> search() {
        fillAllowedClasses();
        return filter(packageSearcher.search(), allowedOrBaseClassOnly());
    }

    private void fillAllowedClasses() {
        Reflections reflections = new ReflectionsBuilder(baseClass).make();
        allowedClasses = reflections.getSubTypesOf(baseClass);
    }

    private Predicate<Class<?>> allowedOrBaseClassOnly() {
        return new Predicate<Class<?>>() {
            public boolean apply(@Nullable Class<?> aClass) {
                return allowedClasses.contains(aClass) || aClass.equals(baseClass);
            }
        };
    }
}
