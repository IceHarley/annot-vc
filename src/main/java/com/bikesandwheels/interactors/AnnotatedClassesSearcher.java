package com.bikesandwheels.interactors;

import java.net.URL;
import java.util.Set;

public interface AnnotatedClassesSearcher {
    Set<Class<?>> search();
    void setUrl(URL url);
}
