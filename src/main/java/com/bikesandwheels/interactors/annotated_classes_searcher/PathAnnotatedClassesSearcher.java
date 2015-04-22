package com.bikesandwheels.interactors.annotated_classes_searcher;

import com.bikesandwheels.interactors.*;
import com.google.common.collect.Sets;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Set;

@Component
public class PathAnnotatedClassesSearcher implements AnnotatedClassesSearcher {
    private static Logger log = Logger.getLogger(PathAnnotatedClassesSearcher.class);

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
        log.info(String.format("%d annotated classes was found.", annotatedTypes.size()));
        return annotatedTypes;
    }

    public void setUrl(URL url) {
        reflectionTools.setUrl(url);
    }
}
