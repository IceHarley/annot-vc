package com.bikesandwheels.domain;

import com.bikesandwheels.annotations.wrappers.RevisionWrapper;

import java.util.Set;

public class RevisedObject {
    private Set<RevisionWrapper> revisions;
    private Class aClass;

    public RevisedObject(Set<RevisionWrapper> revisions, Class aClass) {
        this.revisions = revisions;
        this.aClass = aClass;
    }

    public Set<RevisionWrapper> getRevisions() {
        return revisions;
    }

    public Class getObjectClass() {
        return aClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RevisedObject that = (RevisedObject) o;

        if (!aClass.equals(that.aClass)) return false;
        if (!revisions.equals(that.revisions)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = revisions.hashCode();
        result = 31 * result + aClass.hashCode();
        return result;
    }
}
