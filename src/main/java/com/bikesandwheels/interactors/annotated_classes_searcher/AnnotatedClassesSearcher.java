package com.bikesandwheels.interactors.annotated_classes_searcher;

import com.bikesandwheels.interactors.ReflectionTools;

import java.net.URL;
import java.util.Set;

public interface AnnotatedClassesSearcher {
    @SuppressWarnings("unchecked")
    Set<Class<?>> search();
    void setUrl(URL url);
}
