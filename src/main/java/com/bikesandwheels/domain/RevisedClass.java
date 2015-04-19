package com.bikesandwheels.domain;

import com.bikesandwheels.annotations.wrappers.RevisionWrapper;

import java.util.Set;

public class RevisedClass implements RevisedObject {
    private final Set<RevisionWrapper> revisions;
    private final Class aClass;

    public RevisedClass(Set<RevisionWrapper> revisions, Class aClass) {
        this.revisions = revisions;
        this.aClass = aClass;
    }

    public Set<RevisionWrapper> getRevisions() {
        return revisions;
    }

    public boolean hasRevisions() {
        return !revisions.isEmpty();
    }

    public String getClassName() {
        return aClass.getCanonicalName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RevisedClass that = (RevisedClass) o;

        return revisions.equals(that.revisions) && aClass.equals(that.aClass);

    }

    @Override
    public int hashCode() {
        int result = revisions.hashCode();
        result = 31 * result + aClass.hashCode();
        return result;
    }
}
