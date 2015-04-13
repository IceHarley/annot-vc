package com.bikesandwheels.main;

import com.bikesandwheels.annotations.*;
import com.bikesandwheels.interactors.*;
import com.bikesandwheels.interactors.annotated_classes_searcher.*;
import com.bikesandwheels.interactors.annotated_classes_searcher.scanners.*;
import com.bikesandwheels.interactors.revised_objects_searcher.*;
import com.bikesandwheels.interactors.revised_objects_searcher.scanners.*;
import com.bikesandwheels.tools.ReflectionsFacade;
import org.springframework.beans.factory.config.BeanDefinition;
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

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public RevisedSearcher revisedObjectsSearcher() {
        return new RevisedObjectsSearcher();
    }

    @Bean
    public RevisionsScanner revisedClassRevisionsScanner() {
        return new RevisedClassRevisionsScanner();
    }

    @Bean
    public RevisionsScanner revisedMethodsRevisionsScanner() {
        return new RevisedMethodsRevisionsScanner();
    }

    @Bean
    public RevisionsScanner historyRevisedClassRevisionsScanner() {
        return new HistoryRevisedClassRevisionsScanner();
    }

    @Bean
    public RevisionsScanner historyRevisedMethodsRevisionsScanner() {
        return new HistoryRevisedMethodsRevisionsScanner();
    }
}
