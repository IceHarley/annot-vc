package com.bikesandwheels.interactors.annotated_classes_searcher.scanners;

import com.bikesandwheels.interactors.annotated_classes_searcher.AnnotatedScanner;
import com.google.common.collect.Sets;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Set;

public class ClassesAnnotatedScanner implements AnnotatedScanner {
    private Class<? extends Annotation>[] annotations;
    private Reflections reflections;

    public ClassesAnnotatedScanner(Class<? extends Annotation>... annotations) {
        this.annotations = annotations;
    }

    public void setReflections(Reflections reflections) {
        this.reflections = reflections;
    }

    public Set<Class<?>> scan() {
        Set<Class<?>> annotatedTypes = Sets.newHashSet();
        for (Class<? extends Annotation> annotation : annotations)
            annotatedTypes.addAll(reflections.getTypesAnnotatedWith(annotation));
        return annotatedTypes;
    }
}
