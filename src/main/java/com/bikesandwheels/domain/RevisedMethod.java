package com.bikesandwheels.domain;

import com.bikesandwheels.annotations.wrappers.RevisionWrapper;

import java.lang.reflect.Method;
import java.util.Set;

public class RevisedMethod implements RevisedObject {
    private final Set<RevisionWrapper> revisions;
    private final Method method;

    public RevisedMethod(Set<RevisionWrapper> revisions, Method method) {
        this.revisions = revisions;
        this.method = method;
    }

    public Set<RevisionWrapper> getRevisions() {
        return revisions;
    }

    public boolean hasRevisions() {
        return !revisions.isEmpty();
    }

    public Class getObjectClass() {
        return method.getDeclaringClass();
    }
}
