package com.bikesandwheels.annotations.wrappers;

import com.bikesandwheels.annotations.*;

import java.util.*;

public class HistoryWrapper {
    private final List<RevisionWrapper> revisions = new ArrayList<RevisionWrapper>();

    public HistoryWrapper(History history) {
        if (history.value() != null)
            for (Revision revision : history.value())
                revisions.add(new RevisionWrapper(revision));
    }

    public List<RevisionWrapper> getRevisions() {
        return revisions;
    }
}
