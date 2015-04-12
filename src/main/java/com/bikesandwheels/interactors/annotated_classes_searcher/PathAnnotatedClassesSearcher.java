package com.bikesandwheels.interactors.annotated_classes_searcher;

import com.google.common.collect.Sets;
import org.reflections.*;

import java.util.*;

public class PathAnnotatedClassesSearcher {
    private final Reflections reflections;
    private Set<AnnotatedScanner> scanners = Sets.newHashSet();

    public PathAnnotatedClassesSearcher(Reflections reflections, Set<AnnotatedScanner> scanners) {
        this.reflections = reflections;
        if (scanners != null)
            this.scanners = scanners;
    }

    @SuppressWarnings("unchecked")
    public Set<Class<?>> search() {
        Set<Class<?>> annotatedTypes = Sets.newHashSet();
        for (AnnotatedScanner scanner : scanners) {
            scanner.setReflections(reflections);
            annotatedTypes.addAll(scanner.scan());
        }
        return annotatedTypes;
    }
}
