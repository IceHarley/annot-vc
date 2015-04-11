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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RevisedMethod that = (RevisedMethod) o;

        return revisions.equals(that.revisions) && method.equals(that.method);
    }

    @Override
    public int hashCode() {
        int result = revisions.hashCode();
        result = 31 * result + method.hashCode();
        return result;
    }
}
