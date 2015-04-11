package com.bikesandwheels.interactors.revised_objects_searcher;

import com.bikesandwheels.domain.*;
import com.bikesandwheels.interactors.revised_objects_searcher.scanners.*;
import com.google.common.collect.Sets;
import java.util.*;

public class RevisedObjectsSearcher {
    private final Set<RevisionsScanner> scanners = Sets.newHashSet();
    private final Set<Class<?>> classes;
    private ClassesRevisedObjectsMap allRevisedObjects = null;

    public RevisedObjectsSearcher(Set<Class<?>> classes) {
        this.classes = classes;
        registerScanners();
    }

    private void registerScanners() {
        scanners.add(new RevisedClassRevisionsScanner());
        scanners.add(new ClassWithHistoryRevisionsScanner());
        scanners.add(new RevisedMethodsRevisionsScanner());
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
