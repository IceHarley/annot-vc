package com.bikesandwheels.main;

import com.bikesandwheels.annotations.*;
import com.bikesandwheels.interactors.ReflectionTools;
import com.bikesandwheels.interactors.annotated_classes_searcher.*;
import com.bikesandwheels.interactors.annotated_classes_searcher.scanners.*;
import com.bikesandwheels.tools.ReflectionsFacade;
import org.springframework.context.annotation.*;

@Configuration
public class Config {
    @Bean
    public AnnotatedScanner classesAnnotatedScanner() {
        return new ClassesAnnotatedScanner(getRevisionAnnotations());
    }

    @Bean
    public AnnotatedScanner MethodsAnnotatedScanner() {
        return new MethodsAnnotatedScanner(getRevisionAnnotations());
    }

    private Class[] getRevisionAnnotations() {
        return new Class[]{Revision.class, History.class};
    }

    @Bean
    public AnnotatedClassesSearcher annotatedClassesSearcher() {
        return new PathAnnotatedClassesSearcher();
    }

    @Bean
    public ReflectionTools reflectionTools() {
        return new ReflectionsFacade();
    }
}
