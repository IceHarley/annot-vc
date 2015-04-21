package com.bikesandwheels.config;

import com.bikesandwheels.interactors.AnnotatedClassesSearcher;
import com.bikesandwheels.interactors.annotated_classes_searcher.PathAnnotatedClassesSearcher;
import com.bikesandwheels.tools.HierarchyAnnotatedClassesSearcher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

@Configuration
@Profile(Profiles.TEST)
public class TestClassSearcherConfig {
    @Bean
    @Qualifier("Hierarchy")
    public HierarchyAnnotatedClassesSearcher hierarchyAnnotatedClassesSearcher() {
        return new HierarchyAnnotatedClassesSearcher();
    }
}
