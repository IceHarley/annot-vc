package com.bikesandwheels.interactors.revised_objects_searcher.scanners;

import com.bikesandwheels.domain.annotation_wrappers.RevisionWrapper;

import java.lang.annotation.Annotation;
import java.util.Set;

public class RevisedClassRevisionsScanner extends ClassRevisionsScanner {
    @Override
    protected Set<RevisionWrapper> getWrappedRevisions(Class aClass) {
        return wrapRevisions(getRevisions(aClass));
    }

    @SuppressWarnings({"unchecked"})
    private Set<Annotation> getRevisions(Class aClass) {
        return getReflectionTools().getAnnotations(aClass, Predicates.REVISION_PREDICATE);
    }

    private Set<RevisionWrapper> wrapRevisions(Set<Annotation> revisions) {
        return RevisionWrapper.wrapAll(revisions);
    }
}
