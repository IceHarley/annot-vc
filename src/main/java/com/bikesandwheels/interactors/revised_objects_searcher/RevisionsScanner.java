package com.bikesandwheels.interactors.revised_objects_searcher;

import com.bikesandwheels.annotations.wrappers.RevisionWrapper;
import java.util.Set;

public interface RevisionsScanner {
    Set<RevisionWrapper> scan(Class aClass);
}
