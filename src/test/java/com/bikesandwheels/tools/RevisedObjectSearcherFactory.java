package com.bikesandwheels.tools;

import com.bikesandwheels.interactors.RevisedSearcher;
import com.bikesandwheels.interactors.revised_objects_searcher.*;

import java.util.Set;

public class RevisedObjectSearcherFactory {
    public static RevisedSearcher make(Set<Class<?>> classes) {
        RevisedSearcher revisedSearcher = new RevisedObjectsSearcher();
        revisedSearcher.setClasses(classes);
        return revisedSearcher;
    }
}
