package com.bikesandwheels.interactors;

import com.bikesandwheels.annotations.wrappers.RevisionWrapper;
import java.util.Set;

public interface RevisionsScanner {
    Set<RevisionWrapper> scan(Class aClass);
}
