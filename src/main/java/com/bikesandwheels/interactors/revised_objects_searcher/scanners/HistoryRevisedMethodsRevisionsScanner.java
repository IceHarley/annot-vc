package com.bikesandwheels.interactors.revised_objects_searcher.scanners;

import com.bikesandwheels.annotations.wrappers.*;
import com.google.common.collect.Sets;
import org.reflections.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

public class HistoryRevisedMethodsRevisionsScanner extends MethodsRevisionsScanner {
    @Override
    protected Set<RevisionWrapper> getWrappedRevisions(Method method) {
        Set<RevisionWrapper> wrappedRevisions = Sets.newHashSet();
        for (HistoryWrapper history : getWrappedHistories(method))
            wrappedRevisions.addAll(history.getRevisions());
        return wrappedRevisions;
    }

    private Set<HistoryWrapper> getWrappedHistories(Method method) {
        return wrapHistories(ReflectionUtils.getAnnotations(method, Predicates.HISTORY_PREDICATE));
    }

    private Set<HistoryWrapper> wrapHistories(Set<Annotation> histories) {
        return HistoryWrapper.wrapAll(histories);
    }
}