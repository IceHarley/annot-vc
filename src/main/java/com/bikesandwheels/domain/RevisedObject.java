package com.bikesandwheels.domain;

import com.bikesandwheels.interactors.annotation_wrappers.RevisionWrapper;

import java.util.Set;

public interface RevisedObject {
    Set<RevisionWrapper> getRevisions();
    boolean hasRevisions();
    void accept(RevisedObjectVisitor visitor);
}
