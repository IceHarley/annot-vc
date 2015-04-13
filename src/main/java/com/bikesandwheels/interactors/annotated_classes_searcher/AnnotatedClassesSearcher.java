package com.bikesandwheels.interactors.annotated_classes_searcher;

import java.net.URL;
import java.util.Set;

public interface AnnotatedClassesSearcher {
    Set<Class<?>> search();
    void setUrl(URL url);
}
