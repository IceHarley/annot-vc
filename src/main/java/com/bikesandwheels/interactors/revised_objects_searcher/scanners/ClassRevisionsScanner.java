package com.bikesandwheels.interactors.revised_objects_searcher.scanners;

import com.bikesandwheels.domain.annotation_wrappers.*;
import com.bikesandwheels.domain.*;
import com.bikesandwheels.interactors.ReflectionTools;
import com.bikesandwheels.interactors.revised_objects_searcher.RevisionsScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
abstract class ClassRevisionsScanner implements RevisionsScanner {
    @Autowired
    private ReflectionTools reflectionTools;

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

    ReflectionTools getReflectionTools() {
        return reflectionTools;
    }
}
