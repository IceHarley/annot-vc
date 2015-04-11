package com.bikesandwheels.domain;

import com.bikesandwheels.annotations.wrappers.RevisionWrapper;
import com.google.common.collect.*;

import java.util.*;

public class ClassesRevisedObjectsMap {
    private final Map<Class<?>, RevisedObjects> items = Maps.newHashMap();

    public void add(Class<?> aClass, RevisedObjects revisedObjects) {
        if (items.containsKey(aClass)) {
            items.get(aClass).addAll(revisedObjects);
        } else {
            items.put(aClass, revisedObjects);
        }
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public Set<Class<?>> getClasses() {
        return items.keySet();
    }

    public boolean containsClass(Class<?> aClass) {
        return items.keySet().contains(aClass);
    }

    private RevisedObjects getClassRevisedObjects(Class<?> revisedClass) {
        if (!containsClass(revisedClass))
            return RevisedObjects.EMPTY;
        return items.get(revisedClass);
    }

    public Set<RevisionWrapper> getRevisions(Class<?> revisedClass) {
        if (!containsClass(revisedClass))
            return Sets.newHashSet();
        return getClassRevisedObjects(revisedClass).getAllRevisions();
    }
}
