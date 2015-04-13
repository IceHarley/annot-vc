package com.bikesandwheels.interactors.annotated_classes_searcher;

import com.bikesandwheels.interactors.ReflectionTools;

import java.util.Set;

public interface AnnotatedScanner {
    Set<Class<?>> scan();
    void setReflectionTools(ReflectionTools reflectionTools);
}
