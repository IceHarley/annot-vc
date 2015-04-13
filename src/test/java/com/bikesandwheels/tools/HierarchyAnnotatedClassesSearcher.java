package com.bikesandwheels.tools;

import com.bikesandwheels.interactors.ReflectionTools;
import com.bikesandwheels.interactors.annotated_classes_searcher.*;
import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nullable;
import java.net.URL;
import java.util.Set;

import static com.google.common.collect.Sets.filter;
import static org.reflections.util.ClasspathHelper.forClass;

public class HierarchyAnnotatedClassesSearcher implements AnnotatedClassesSearcher {
    @Autowired
    private PathAnnotatedClassesSearcher packageSearcher;
    @Autowired
    private ReflectionTools reflectionTools;
    private Class<?> baseClass;
    private Set<? extends Class<?>> derivedClasses;

    public HierarchyAnnotatedClassesSearcher() {
    }

    public void setUrl(URL url) {
        packageSearcher.setUrl(url);
        reflectionTools.setUrl(url);
    }

    public void setBaseClass(Class<?> baseClass) {
        this.baseClass = baseClass;
        setUrl(forClass(baseClass));
    }

    public Set<Class<?>> search() {
        fillAllowedClasses();
        return filter(packageSearcher.search(), baseOrDerivedOnly());
    }

    private void fillAllowedClasses() {
        derivedClasses = reflectionTools.getSubTypesOf(baseClass);
    }

    private Predicate<Class<?>> baseOrDerivedOnly() {
        return new Predicate<Class<?>>() {
            public boolean apply(@Nullable Class<?> aClass) {
                return derivedClasses.contains(aClass) || baseClass.equals(aClass);
            }
        };
    }
}
