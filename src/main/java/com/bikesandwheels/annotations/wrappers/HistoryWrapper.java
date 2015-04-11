package com.bikesandwheels.annotations.wrappers;

import com.bikesandwheels.annotations.*;
import com.google.common.collect.Sets;

import java.lang.annotation.Annotation;
import java.util.*;

public class HistoryWrapper {
    private final Set<RevisionWrapper> revisions = Sets.newHashSet();

    public HistoryWrapper(History history) {
        if (history.value() != null)
            for (Revision revision : history.value())
                revisions.add(new RevisionWrapper(revision));
    }

    public Set<RevisionWrapper> getRevisions() {
        return revisions;
    }

    public static Set<HistoryWrapper> wrapAll(Set<? extends Annotation> history) {
        Set<HistoryWrapper> wrappers = Sets.newHashSet();
        for (Annotation annotation : history)
            wrappers.add(new HistoryWrapper((History) annotation));
        return wrappers;
    }

    public boolean isEmpty() {
        return revisions.isEmpty();
    }
}
