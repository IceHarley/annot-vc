package com.bikesandwheels.interactors.revised_objects_searcher;

import com.bikesandwheels.annotations.wrappers.*;
import com.bikesandwheels.domain.*;
import com.bikesandwheels.interactors.revised_objects_searcher.scanners.*;
import com.google.common.collect.Sets;
import java.util.*;

public class RevisedObjectsSearcher {
    Set<RevisionsScanner> scanners = Sets.newHashSet();
    private Set<Class<?>> classes;
    RevisedObjects revisedObjects = null;

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
    public RevisedObjects findAllRevisedObjects() {
        if (revisedObjects == null)
            analyzeRevisedObjects();
        return revisedObjects;
    }

    private void analyzeRevisedObjects() {
        revisedObjects = new RevisedObjects();
        for (Class<?> aClass : classes) {
            Set<RevisionWrapper> revisionAnnotations = getAnnotations(aClass);
            addRevisedObject(aClass, revisionAnnotations);
        }
    }

    private Set<RevisionWrapper> getAnnotations(Class<?> aClass) {
        Set<RevisionWrapper> revisions = Sets.newHashSet();
        for (RevisionsScanner scanner : scanners)
            revisions.addAll(scanner.scan(aClass));
        return revisions;
    }

    private void addRevisedObject(Class<?> aClass, Set<RevisionWrapper> revisions) {
        if (!revisions.isEmpty()) {
            RevisedObject revisedObject = new RevisedObject(revisions, aClass);
            revisedObjects.add(revisedObject);
        }
    }
}
