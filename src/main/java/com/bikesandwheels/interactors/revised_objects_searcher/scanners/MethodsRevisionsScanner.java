package com.bikesandwheels.interactors.revised_objects_searcher.scanners;

import com.bikesandwheels.annotations.wrappers.RevisionWrapper;
import com.bikesandwheels.domain.*;
import com.bikesandwheels.interactors.revised_objects_searcher.RevisionsScanner;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Set;

abstract class MethodsRevisionsScanner implements RevisionsScanner {
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
        return new RevisedMethod(getWrappedRevisions(method), method);
    }

    protected abstract Set<RevisionWrapper> getWrappedRevisions(Method method);
}
