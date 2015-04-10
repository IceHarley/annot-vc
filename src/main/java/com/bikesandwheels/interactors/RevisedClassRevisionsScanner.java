package com.bikesandwheels.interactors;

import com.bikesandwheels.annotations.wrappers.RevisionWrapper;
import org.reflections.ReflectionUtils;
import java.util.Set;

public class RevisedClassRevisionsScanner implements RevisionsScanner {
    @SuppressWarnings("unchecked")
    public Set<RevisionWrapper> scan(Class aClass) {
        return RevisionWrapper.wrapAll(ReflectionUtils.getAnnotations(aClass, Predicates.REVISION_PREDICATE));
    }
}
