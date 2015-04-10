package com.bikesandwheels.domain;

import com.bikesandwheels.annotations.wrappers.RevisionWrapper;
import com.google.common.collect.*;

import java.util.*;

public class RevisedObjects {
    Map<Class<?>, RevisedObject> items = Maps.newHashMap();

    public void add(RevisedObject item) {
        items.put(item.getObjectClass(), item);
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

    public Set<RevisionWrapper> getRevisions(Class<?> revisedClass) {
        if (!containsClass(revisedClass))
            return Sets.newHashSet();
        return items.get(revisedClass).getRevisions();
    }
}
