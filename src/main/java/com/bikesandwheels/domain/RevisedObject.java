package com.bikesandwheels.domain;

import com.bikesandwheels.annotations.wrappers.RevisionWrapper;

import java.util.Set;

public interface RevisedObject {
    Set<RevisionWrapper> getRevisions();
    boolean hasRevisions();
    Class getObjectClass();
}
