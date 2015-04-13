package com.bikesandwheels.interactors.annotated_classes_searcher;

import com.bikesandwheels.interactors.*;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Set;

@Component
public class PathAnnotatedClassesSearcher implements AnnotatedClassesSearcher {
    @Autowired
    private ReflectionTools reflectionTools;
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @Autowired
    private Set<AnnotatedScanner> scanners;

    @SuppressWarnings("unchecked")
    public Set<Class<?>> search() {
        Set<Class<?>> annotatedTypes = Sets.newHashSet();
        for (AnnotatedScanner scanner : scanners)
            annotatedTypes.addAll(scanner.scan());
        return annotatedTypes;
    }

    public void setUrl(URL url) {
        reflectionTools.setUrl(url);
    }
}
