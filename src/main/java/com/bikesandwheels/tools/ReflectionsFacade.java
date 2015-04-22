package com.bikesandwheels.tools;

import com.bikesandwheels.interactors.ReflectionTools;
import com.google.common.base.Predicate;
import org.reflections.*;
import org.reflections.scanners.*;
import org.reflections.util.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.*;
import java.util.Set;

public class ReflectionsFacade implements ReflectionTools {
    private Reflections reflections;
    private URL url;

    public void setUrl(URL url) {
        if (url != null && !url.equals(this.url)) {
            this.url = url;
            reflections = new ReflectionsBuilder(url).make();
        }
    }

    public Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotation) {
        return reflections.getTypesAnnotatedWith(annotation);
    }

    public Set<Method> getMethodsAnnotatedWith(Class<? extends Annotation> annotation) {
        return reflections.getMethodsAnnotatedWith(annotation);
    }

    public <T> Set<Class<? extends T>> getSubTypesOf(Class<T> type) {
        return reflections.getSubTypesOf(type);
    }

    @SuppressWarnings("unchecked")
    public Set<Method> getMethods(Class<?> aClass) {
        return ReflectionUtils.getMethods(aClass);
    }

    public <T extends AnnotatedElement> Set<Annotation> getAnnotations(T type, Predicate<Annotation>... predicates) {
        return ReflectionUtils.getAnnotations(type, predicates);
    }

    private class ReflectionsBuilder {
        private final ConfigurationBuilder configurationBuilder;

        public ReflectionsBuilder(URL url) {
            URL[] urls = new URL[]{ url };
            this.configurationBuilder = new ConfigurationBuilder()
                    .setUrls(url)
                    .setScanners(
                            new SubTypesScanner(true),
                            new TypeAnnotationsScanner(),
                            new MethodAnnotationsScanner())
                    .addClassLoader(new URLClassLoader(urls));
        }

        public Reflections make() {
            return new Reflections(configurationBuilder);
        }
    }
}
