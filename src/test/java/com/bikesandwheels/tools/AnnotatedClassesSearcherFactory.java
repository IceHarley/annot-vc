package com.bikesandwheels.tools;

import com.bikesandwheels.annotations.*;
import com.bikesandwheels.interactors.annotated_classes_searcher.*;
import com.bikesandwheels.interactors.annotated_classes_searcher.scanners.*;
import com.google.common.collect.Sets;
import org.reflections.Reflections;

import java.net.MalformedURLException;
import java.util.Set;

public class AnnotatedClassesSearcherFactory {
    public static HierarchyAnnotatedClassesSearcher make(Class baseClass) throws MalformedURLException {
        Reflections reflections = new ReflectionsBuilder(baseClass).make();
        return new HierarchyAnnotatedClassesSearcher(baseClass, reflections, registerAllScanners());
    }

    private static Set<AnnotatedScanner> registerAllScanners() {
        return Sets.newHashSet(
                new ClassesAnnotatedScanner(Revision.class, History.class),
                new MethodsAnnotatedScanner(Revision.class, History.class));
    }
}
