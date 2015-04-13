package com.bikesandwheels.interactors.revised_objects_searcher.scanners;

import com.bikesandwheels.annotations.wrappers.RevisionWrapper;
import com.bikesandwheels.interactors.ReflectionTools;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

public class RevisedMethodsRevisionsScanner extends MethodsRevisionsScanner {
    public RevisedMethodsRevisionsScanner(ReflectionTools reflectionTools) {
        super(reflectionTools);
    }

    @Override
    protected Set<RevisionWrapper> getWrappedRevisions(Method method) {
        return wrapRevisions(getRevisions(method));
    }

    private Set<Annotation> getRevisions(Method method) {
        return getReflectionTools().getAnnotations(method, Predicates.REVISION_PREDICATE);
    }

    private Set<RevisionWrapper> wrapRevisions(Set<Annotation> revisions) {
        return RevisionWrapper.wrapAll(revisions);
    }
}
