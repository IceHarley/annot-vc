package com.bikesandwheels.config;

import com.bikesandwheels.interactors.*;
import com.bikesandwheels.interactors.annotated_classes_searcher.PathAnnotatedClassesSearcher;
import org.springframework.context.annotation.*;

@Configuration
@Profile(Profiles.LIVE)
public class ClassSearcherConfig {
    @Bean
    public AnnotatedClassesSearcher annotatedClassesSearcher() {
        return new PathAnnotatedClassesSearcher();
    }
}
