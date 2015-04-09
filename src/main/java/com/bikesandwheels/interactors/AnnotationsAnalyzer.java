package com.bikesandwheels.interactors;

import com.bikesandwheels.annotations.Revision;
import com.bikesandwheels.annotations.wrappers.RevisionWrapper;
import com.bikesandwheels.domain.RevisedObject;
import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import org.reflections.ReflectionUtils;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.util.*;

public class AnnotationsAnalyzer {
    private static final Predicate<Annotation> REVISION_PREDICATE = new Predicate<Annotation>() {
        public boolean apply(@Nullable Annotation annotation) {
            return annotation instanceof Revision;
        }
    };
    private Set<Class<?>> classes;

    public AnnotationsAnalyzer(Set<Class<?>> classes) {
        this.classes = classes;
    }

    public Set<RevisedObject> getRevisedObjects() {
        Set<RevisedObject> revisedObjects = Sets.newHashSet();
        for (Class<?> aClass : classes) {
            Set<Annotation> annotations = ReflectionUtils.getAnnotations(aClass, REVISION_PREDICATE);
            Set<RevisionWrapper> revisions = Sets.newHashSet();
            for (Annotation annotation : annotations)
                revisions.add(new RevisionWrapper((Revision) annotation));
            revisedObjects.add(new RevisedObject(revisions, aClass));
        }
        return revisedObjects;
    }

}
