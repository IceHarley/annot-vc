package com.bikesandwheels.interactors.revised_objects_searcher.scanners;

import com.bikesandwheels.annotations.wrappers.RevisionWrapper;
import com.bikesandwheels.domain.*;
import com.bikesandwheels.interactors.revised_objects_searcher.RevisionsScanner;
import org.reflections.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.util.Set;

public class RevisedClassRevisionsScanner implements RevisionsScanner {
    @SuppressWarnings("unchecked")
    public RevisedObjects scan(Class aClass) {
        Set<RevisionWrapper> wrappers = wrapRevisions(scanForRevisions(aClass));
        return getRevisedObjects(aClass, wrappers);
    }

    private RevisedObjects getRevisedObjects(Class aClass, Set<RevisionWrapper> wrappers) {
        RevisedObjects revisedObjects = new RevisedObjects();
        if (wrappers.size() > 0)
            revisedObjects.add(new RevisedClass(wrappers, aClass));
        return revisedObjects;
    }

    private Set<RevisionWrapper> wrapRevisions(Set<Annotation> revisions) {
        return RevisionWrapper.wrapAll(revisions);
    }

    private Set<Annotation> scanForRevisions(Class aClass) {
        return ReflectionUtils.getAnnotations(aClass, Predicates.REVISION_PREDICATE);
    }
}
