package com.bikesandwheels.interactors.annotated_classes_searcher;

import org.reflections.Reflections;

import java.util.Set;

public interface AnnotatedScanner {
    Set<Class<?>> scan();
    void setReflections(Reflections reflections);
}
