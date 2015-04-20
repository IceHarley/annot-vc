package com.bikesandwheels.config;

import com.bikesandwheels.annotations.*;
import com.bikesandwheels.interactors.*;
import com.bikesandwheels.interactors.annotated_classes_searcher.AnnotatedScanner;
import com.bikesandwheels.interactors.annotated_classes_searcher.scanners.*;
import com.bikesandwheels.interactors.revised_objects_searcher.*;
import com.bikesandwheels.interactors.revised_objects_searcher.scanners.*;
import com.bikesandwheels.main.AppRunner;
import com.bikesandwheels.tools.ReflectionsFacade;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;

import java.lang.annotation.Annotation;

@Configuration
@Import(value = {RepositoryConfig.class, ClassSearcherConfig.class, ServiceConfig.class})
public class AppConfig {
    @Bean
    public Runnable appRunner() {
        return new AppRunner();
    }

    @Bean
    public Class<? extends Annotation> revisionAnnotation() {
        return Revision.class;
    }

    @Bean
    public Class<? extends Annotation> historyAnnotation() {
        return History.class;
    }

    @Bean
    public AnnotatedScanner classesAnnotatedScanner() {
        return new ClassesAnnotatedScanner();
    }

    @Bean
    public AnnotatedScanner methodsAnnotatedScanner() {
        return new MethodsAnnotatedScanner();
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
