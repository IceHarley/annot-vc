package com.bikesandwheels.interactors.revised_objects_searcher.scanners;

import com.bikesandwheels.interactors.annotation_wrappers.RevisionWrapper;
import com.bikesandwheels.domain.*;
import com.bikesandwheels.interactors.ReflectionTools;
import com.bikesandwheels.interactors.revised_objects_searcher.RevisionsScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Set;

@Component
abstract class MethodsRevisionsScanner implements RevisionsScanner {
    @Autowired
    private ReflectionTools reflectionTools;

    public RevisedObjects scan(Class aClass) {
        return getRevisedObjects(getRevisedMethods(aClass));
    }

    @SuppressWarnings("unchecked")
    private Set<Method> getRevisedMethods(Class aClass) {
        return reflectionTools.getMethods(aClass);
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

    protected ReflectionTools getReflectionTools() {
        return reflectionTools;
    }
}
