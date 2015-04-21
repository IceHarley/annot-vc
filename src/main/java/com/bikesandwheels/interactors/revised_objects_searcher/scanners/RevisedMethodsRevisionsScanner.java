package com.bikesandwheels.interactors.revised_objects_searcher.scanners;

import com.bikesandwheels.interactors.annotation_wrappers.RevisionWrapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

public class RevisedMethodsRevisionsScanner extends MethodsRevisionsScanner {
    @Override
    protected Set<RevisionWrapper> getWrappedRevisions(Method method) {
        return wrapRevisions(getRevisions(method));
    }

    @SuppressWarnings({"unchecked"})
    private Set<Annotation> getRevisions(Method method) {
        return getReflectionTools().getAnnotations(method, Predicates.REVISION_PREDICATE);
    }

    private Set<RevisionWrapper> wrapRevisions(Set<Annotation> revisions) {
        return RevisionWrapper.wrapAll(revisions);
    }
}
