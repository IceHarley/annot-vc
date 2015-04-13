package com.bikesandwheels.interactors;

import com.bikesandwheels.interactors.annotated_classes_searcher.*;
import com.bikesandwheels.main.Config;
import com.bikesandwheels.tools.HierarchyAnnotatedClassesSearcher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

@Configuration
public class TestConfig extends Config {
    @Bean
    @Override
    public AnnotatedClassesSearcher annotatedClassesSearcher() {
        return new HierarchyAnnotatedClassesSearcher();
    }

    @Bean
    public PathAnnotatedClassesSearcher pathAnnotatedClassesSearcher() {
        return new PathAnnotatedClassesSearcher();
    }

    @Bean
    @Qualifier("Hierarchy")
    public HierarchyAnnotatedClassesSearcher hierarchyAnnotatedClassesSearcher() {
        return new HierarchyAnnotatedClassesSearcher();
    }
}
