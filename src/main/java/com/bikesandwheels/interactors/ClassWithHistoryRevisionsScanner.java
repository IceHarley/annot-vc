package com.bikesandwheels.interactors;

import com.bikesandwheels.annotations.History;
import com.bikesandwheels.annotations.wrappers.*;
import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import org.reflections.ReflectionUtils;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
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
