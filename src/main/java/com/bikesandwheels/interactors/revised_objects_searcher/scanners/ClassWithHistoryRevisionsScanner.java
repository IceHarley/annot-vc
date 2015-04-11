package com.bikesandwheels.interactors.revised_objects_searcher.scanners;

import com.bikesandwheels.annotations.wrappers.*;
import com.bikesandwheels.domain.*;
import com.bikesandwheels.interactors.revised_objects_searcher.RevisionsScanner;
import org.reflections.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.util.Set;

public class ClassWithHistoryRevisionsScanner implements RevisionsScanner {
    @SuppressWarnings("unchecked")
    public RevisedObjects scan(Class aClass) {
        Set<HistoryWrapper> histories = wrapRevisions(getRevisions(aClass));
        return getRevisedObjects(aClass, histories);
    }

    private RevisedObjects getRevisedObjects(Class aClass, Set<HistoryWrapper> histories) {
        RevisedObjects revisedObjects = new RevisedObjects();
        for (HistoryWrapper history : histories)
            if (!history.isEmpty())
                revisedObjects.add(new RevisedClass(history.getRevisions(), aClass));
        return revisedObjects;
    }

    private Set<HistoryWrapper> wrapRevisions(Set<Annotation> revisions) {
        return HistoryWrapper.wrapAll(revisions);
    }

    private Set<Annotation> getRevisions(Class aClass) {
        return ReflectionUtils.getAnnotations(aClass, Predicates.HISTORY_PREDICATE);
    }
}
