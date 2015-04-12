package com.bikesandwheels.interactors.revised_objects_searcher;

import com.bikesandwheels.domain.*;
import com.bikesandwheels.interactors.revised_objects_searcher.scanners.*;
import com.google.common.collect.Sets;
import java.util.*;

public class RevisedObjectsSearcher {
    private final Set<Class<?>> classes;
    private Set<RevisionsScanner> scanners = Sets.newHashSet();
    private ClassesRevisedObjectsMap allRevisedObjects = null;

    public RevisedObjectsSearcher(Set<Class<?>> classes, Set<RevisionsScanner> scanners) {
        this.classes = classes;
        if (scanners != null)
            this.scanners = scanners;
    }

    private void registerScanners() {
        scanners.add(new RevisedClassRevisionsScanner());
        scanners.add(new HistoryRevisedClassRevisionsScanner());
        scanners.add(new RevisedMethodsRevisionsScanner());
        scanners.add(new HistoryRevisedMethodsRevisionsScanner());
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
