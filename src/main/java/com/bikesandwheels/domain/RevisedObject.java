package com.bikesandwheels.domain;

import com.bikesandwheels.domain.annotation_wrappers.RevisionWrapper;

import java.util.Set;

public interface RevisedObject {
    Set<RevisionWrapper> getRevisions();
    boolean hasRevisions();
    void accept(RevisedObjectVisitor visitor);
}
