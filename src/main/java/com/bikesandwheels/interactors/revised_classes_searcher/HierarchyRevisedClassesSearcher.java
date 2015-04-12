package com.bikesandwheels.interactors.revised_classes_searcher;

import com.google.common.base.Predicate;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;

import javax.annotation.Nullable;
import java.net.MalformedURLException;
import java.util.Set;

import static com.google.common.collect.Sets.filter;

public class HierarchyRevisedClassesSearcher implements RevisedClassesSearcher {
    private final Class<?> baseClass;
    private PathRevisedClassesSearcher packageSearcher;
    private Set<? extends Class<?>> derivedClasses;

    public HierarchyRevisedClassesSearcher(Class<?> baseClass, Reflections reflections) throws MalformedURLException {
        this.baseClass = baseClass;
        packageSearcher = new PathRevisedClassesSearcher(reflections);
    }

    public Set<Class<?>> search() {
        fillAllowedClasses();
        return filter(packageSearcher.search(), baseOrDerivedOnly());
    }

    private void fillAllowedClasses() {
        Reflections reflections = new ReflectionsBuilder(ClasspathHelper.forClass(baseClass)).make();
        derivedClasses = reflections.getSubTypesOf(baseClass);
    }

    private Predicate<Class<?>> baseOrDerivedOnly() {
        return new Predicate<Class<?>>() {
            public boolean apply(@Nullable Class<?> aClass) {
                return derivedClasses.contains(aClass) || baseClass.equals(aClass);
            }
        };
    }
}
