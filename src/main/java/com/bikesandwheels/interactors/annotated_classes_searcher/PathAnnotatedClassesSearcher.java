package com.bikesandwheels.interactors.annotated_classes_searcher;

import com.bikesandwheels.interactors.ReflectionTools;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.*;

public class PathAnnotatedClassesSearcher implements AnnotatedClassesSearcher {
    @Autowired
    private ReflectionTools reflectionTools;
    @Autowired
    private Set<AnnotatedScanner> scanners;

    @SuppressWarnings("unchecked")
    public Set<Class<?>> search() {
        Set<Class<?>> annotatedTypes = Sets.newHashSet();
        for (AnnotatedScanner scanner : scanners) {
            scanner.setReflectionTools(reflectionTools);
            annotatedTypes.addAll(scanner.scan());
        }
        return annotatedTypes;
    }

    public void setUrl(URL url) {
        reflectionTools.setUrl(url);
    }
}
