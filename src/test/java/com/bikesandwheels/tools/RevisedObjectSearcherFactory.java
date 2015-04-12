package com.bikesandwheels.tools;

import com.bikesandwheels.interactors.revised_objects_searcher.*;
import com.bikesandwheels.interactors.revised_objects_searcher.scanners.*;
import com.google.common.collect.Sets;

import java.util.Set;

public class RevisedObjectSearcherFactory {
    public static RevisedObjectsSearcher make(Set<Class<?>> classes) {
        return new RevisedObjectsSearcher(classes, registerAllScanners());
    }

    private static Set<RevisionsScanner> registerAllScanners() {
        return Sets.newHashSet(
                new RevisedClassRevisionsScanner(),
                new RevisedMethodsRevisionsScanner(),
                new HistoryRevisedClassRevisionsScanner(),
                new HistoryRevisedMethodsRevisionsScanner());
    }
}
