package com.bikesandwheels.interactors.revised_objects_searcher.scanners;

import com.bikesandwheels.annotations.wrappers.RevisionWrapper;
import com.bikesandwheels.interactors.revised_objects_searcher.RevisionsScanner;
import org.reflections.ReflectionUtils;
import java.util.Set;

public class RevisedClassRevisionsScanner implements RevisionsScanner {
    @SuppressWarnings("unchecked")
    public Set<RevisionWrapper> scan(Class aClass) {
        return RevisionWrapper.wrapAll(ReflectionUtils.getAnnotations(aClass, Predicates.REVISION_PREDICATE));
    }
}
