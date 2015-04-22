package com.bikesandwheels.domain;

import com.bikesandwheels.interactors.annotation_wrappers.RevisionWrapper;
import com.bikesandwheels.tools.StringUtils;
import com.google.common.collect.Lists;

import java.lang.reflect.Method;
import java.util.*;

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

    public void accept(RevisedObjectVisitor visitor) {
        visitor.visit(this);
    }

    public String getMethodName() {
        return method.getName();
    }

    public String getMethodSignature() {
        List<String> types = Lists.newArrayList();
        for (Class<?> type : method.getParameterTypes())
            types.add(type.getCanonicalName());
        return StringUtils.join(types, ",");
    }

    public String getDeclaringClassName() {
        return method.getDeclaringClass().getCanonicalName();
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

    @Override
    public String toString() {
        return String.format("%s.%s(%s)", method.getDeclaringClass().getSimpleName(), getMethodName(), getMethodSignature());
    }
}
