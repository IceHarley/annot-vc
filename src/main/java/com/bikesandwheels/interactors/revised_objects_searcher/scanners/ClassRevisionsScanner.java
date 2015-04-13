package com.bikesandwheels.interactors.revised_objects_searcher.scanners;

import com.bikesandwheels.annotations.wrappers.*;
import com.bikesandwheels.domain.*;
import com.bikesandwheels.interactors.ReflectionTools;
import com.bikesandwheels.interactors.revised_objects_searcher.RevisionsScanner;

import java.util.Set;

abstract class ClassRevisionsScanner implements RevisionsScanner {
    private final ReflectionTools reflectionTools;

    protected ClassRevisionsScanner(ReflectionTools reflectionTools) {
        this.reflectionTools = reflectionTools;
    }

    @SuppressWarnings("unchecked")
    public RevisedObjects scan(Class aClass) {
        Set<RevisionWrapper> wrappers = getWrappedRevisions(aClass);
        return getRevisedObjects(aClass, wrappers);
    }

    protected abstract Set<RevisionWrapper> getWrappedRevisions(Class aClass);

    private RevisedObjects getRevisedObjects(Class aClass, Set<RevisionWrapper> wrappers) {
        RevisedObjects revisedObjects = new RevisedObjects();
        if (wrappers.size() > 0)
            revisedObjects.add(new RevisedClass(wrappers, aClass));
        return revisedObjects;
    }

    protected ReflectionTools getReflectionTools() {
        return reflectionTools;
    }
}
