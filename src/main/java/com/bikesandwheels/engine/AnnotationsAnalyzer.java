package com.bikesandwheels.engine;

import com.bikesandwheels.annotations.Revision;
import com.google.common.collect.Sets;
import org.reflections.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.util.*;

import static org.reflections.ReflectionUtils.*;

public class AnnotationsAnalyzer {
    private Set<Class<?>> classes;

    public AnnotationsAnalyzer(Set<Class<?>> classes) {
        this.classes = classes;
    }

    public Set<Annotation> getRevisions() {
        Set<Annotation> annotations = Sets.newHashSet();
        for (Class<?> aClass : classes)
            annotations.addAll(ReflectionUtils.getAllAnnotations(aClass));
        return annotations;
    }
}
