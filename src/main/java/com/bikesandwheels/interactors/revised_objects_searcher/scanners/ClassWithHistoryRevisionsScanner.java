package com.bikesandwheels.interactors.revised_objects_searcher.scanners;

import com.bikesandwheels.annotations.wrappers.*;
import com.bikesandwheels.interactors.revised_objects_searcher.RevisionsScanner;
import com.google.common.collect.Sets;
import org.reflections.ReflectionUtils;

import java.util.Set;

public class ClassWithHistoryRevisionsScanner implements RevisionsScanner {
    @SuppressWarnings("unchecked")
    public Set<RevisionWrapper> scan(Class aClass) {
        Set<RevisionWrapper> revisions = Sets.newHashSet();
        Set<HistoryWrapper> histories = HistoryWrapper.wrapAll(ReflectionUtils.getAnnotations(aClass, Predicates.HISTORY_PREDICATE));
        for (HistoryWrapper history : histories)
            revisions.addAll(history.getRevisions());
        return revisions;
    }
}
