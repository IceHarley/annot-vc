package com.bikesandwheels.interactors;

import com.bikesandwheels.domain.ClassesRevisedObjectsMap;

import java.util.Set;

public interface RevisedSearcher {
    void setClasses(Set<Class<?>> classes);

    @SuppressWarnings("unchecked")
    ClassesRevisedObjectsMap findAllRevisedObjects();
}
