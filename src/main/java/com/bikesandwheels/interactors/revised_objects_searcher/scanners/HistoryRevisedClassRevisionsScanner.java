package com.bikesandwheels.interactors.revised_objects_searcher.scanners;

import com.bikesandwheels.annotations.wrappers.*;
import com.google.common.collect.Sets;

import java.lang.annotation.Annotation;
import java.util.Set;

public class HistoryRevisedClassRevisionsScanner extends ClassRevisionsScanner {
    @Override
    protected Set<RevisionWrapper> getWrappedRevisions(Class aClass) {
        Set<RevisionWrapper> wrappedRevisions = Sets.newHashSet();
        for (HistoryWrapper history : getWrappedHistories(aClass))
            wrappedRevisions.addAll(history.getRevisions());
        return wrappedRevisions;
    }

    private Set<HistoryWrapper> getWrappedHistories(Class aClass) {
        return wrapHistories(getReflectionTools().getAnnotations(aClass, Predicates.HISTORY_PREDICATE));
    }

    private Set<HistoryWrapper> wrapHistories(Set<Annotation> histories) {
        return HistoryWrapper.wrapAll(histories);
    }
}
