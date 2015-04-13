package com.bikesandwheels.interactors.revised_objects_searcher;

import com.bikesandwheels.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RevisedObjectsSearcher implements com.bikesandwheels.interactors.RevisedSearcher {
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @Autowired
    private Set<RevisionsScanner> scanners;
    private Set<Class<?>> classes;
    private ClassesRevisedObjectsMap allRevisedObjects = null;

    public void setClasses(Set<Class<?>> classes) {
        this.classes = classes;
    }

    @SuppressWarnings("unchecked")
    public ClassesRevisedObjectsMap findAllRevisedObjects() {
        if (allRevisedObjects == null)
            fillRevisedObjects();
        return allRevisedObjects;
    }

    private void fillRevisedObjects() {
        allRevisedObjects = new ClassesRevisedObjectsMap();
        for (Class<?> aClass : classes)
            addClassRevisedObject(aClass, getClassRevisedObjects(aClass));
    }

    private RevisedObjects getClassRevisedObjects(Class<?> aClass) {
        RevisedObjects revisedObjects = new RevisedObjects();
        for (RevisionsScanner scanner : scanners)
            revisedObjects.addAll(scanner.scan(aClass));
        return revisedObjects;
    }

    private void addClassRevisedObject(Class<?> aClass, RevisedObjects revisedObjects) {
        if (!revisedObjects.isEmpty())
            allRevisedObjects.add(aClass, revisedObjects);
    }
}
