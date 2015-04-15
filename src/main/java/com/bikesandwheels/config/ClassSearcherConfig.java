package com.bikesandwheels.config;

import com.bikesandwheels.interactors.AnnotatedClassesSearcher;
import com.bikesandwheels.interactors.annotated_classes_searcher.PathAnnotatedClassesSearcher;
import org.springframework.context.annotation.*;

@Configuration
@Profile("live")
public class ClassSearcherConfig {
    @Bean
    public AnnotatedClassesSearcher annotatedClassesSearcher() {
        return new PathAnnotatedClassesSearcher();
    }
}