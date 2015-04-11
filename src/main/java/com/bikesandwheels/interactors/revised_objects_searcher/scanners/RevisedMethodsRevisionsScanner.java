package com.bikesandwheels.interactors.revised_objects_searcher.scanners;

import com.bikesandwheels.annotations.wrappers.RevisionWrapper;
import com.bikesandwheels.domain.*;
import com.bikesandwheels.interactors.revised_objects_searcher.RevisionsScanner;
import org.reflections.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

public class RevisedMethodsRevisionsScanner implements RevisionsScanner {
    public RevisedObjects scan(Class aClass) {
        return getRevisedObjects(getRevisedMethods(aClass));
    }

    @SuppressWarnings("unchecked")
    private Set<Method> getRevisedMethods(Class aClass) {
        return ReflectionUtils.getMethods(aClass);
    }

    private RevisedObjects getRevisedObjects(Set<Method> methods) {
        RevisedObjects revisedObjects = new RevisedObjects();
        for (Method method : methods)
            revisedObjects.add(getMethodRevisedObject(method));
        return revisedObjects;
    }

    private RevisedObject getMethodRevisedObject(Method method) {
        return new RevisedMethod(wrapRevisions(getRevisions(method)), method);
    }

    private Set<Annotation> getRevisions(Method method) {
        return ReflectionUtils.getAnnotations(method, Predicates.REVISION_PREDICATE);
    }

    private Set<RevisionWrapper> wrapRevisions(Set<Annotation> revisions) {
        return RevisionWrapper.wrapAll(revisions);
    }
}
