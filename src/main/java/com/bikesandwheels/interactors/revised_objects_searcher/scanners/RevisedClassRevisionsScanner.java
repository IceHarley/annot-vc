package com.bikesandwheels.interactors.revised_objects_searcher.scanners;

import com.bikesandwheels.annotations.wrappers.RevisionWrapper;
import com.bikesandwheels.interactors.ReflectionTools;

import java.lang.annotation.Annotation;
import java.util.Set;

public class RevisedClassRevisionsScanner extends ClassRevisionsScanner {
    public RevisedClassRevisionsScanner(ReflectionTools reflectionTools) {
        super(reflectionTools);
    }

    @Override
    protected Set<RevisionWrapper> getWrappedRevisions(Class aClass) {
        return wrapRevisions(getRevisions(aClass));
    }

    private Set<Annotation> getRevisions(Class aClass) {
        return getReflectionTools().getAnnotations(aClass, Predicates.REVISION_PREDICATE);
    }

    private Set<RevisionWrapper> wrapRevisions(Set<Annotation> revisions) {
        return RevisionWrapper.wrapAll(revisions);
    }
}
