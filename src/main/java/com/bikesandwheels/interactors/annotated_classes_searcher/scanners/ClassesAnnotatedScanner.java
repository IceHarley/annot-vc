package com.bikesandwheels.interactors.annotated_classes_searcher.scanners;

import com.bikesandwheels.interactors.ReflectionTools;
import com.bikesandwheels.interactors.annotated_classes_searcher.AnnotatedScanner;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Set;

@Component
public class ClassesAnnotatedScanner implements AnnotatedScanner {
    @Autowired
    private Class<? extends Annotation>[] annotations;
    @Autowired
    private ReflectionTools reflectionTools;

    public Set<Class<?>> scan() {
        Set<Class<?>> annotatedTypes = Sets.newHashSet();
        for (Class<? extends Annotation> annotation : annotations)
            annotatedTypes.addAll(reflectionTools.getTypesAnnotatedWith(annotation));
        return annotatedTypes;
    }
}
