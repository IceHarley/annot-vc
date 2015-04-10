package com.bikesandwheels.interactors.revised_objects_searcher.scanners;

import com.bikesandwheels.annotations.wrappers.RevisionWrapper;
import com.bikesandwheels.interactors.revised_objects_searcher.RevisionsScanner;
import com.google.common.collect.Sets;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Set;

public class RevisedMethodsRevisionsScanner implements RevisionsScanner {
    @SuppressWarnings("unchecked")
    public Set<RevisionWrapper> scan(Class aClass) {
        Set<RevisionWrapper> revisions = Sets.newHashSet();
        Set<Method> methods = ReflectionUtils.getMethods(aClass);
        for (Method method : methods)
            revisions.addAll(RevisionWrapper.wrapAll(ReflectionUtils.getAnnotations(method, Predicates.REVISION_PREDICATE)));
        return revisions;
    }
}
