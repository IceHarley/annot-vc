package com.bikesandwheels.interactors.revised_classes_searcher;

import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.*;

import java.net.URL;

public class ReflectionsBuilder {
    private ConfigurationBuilder configurationBuilder;

    public ReflectionsBuilder(URL url) {
        this.configurationBuilder = new ConfigurationBuilder()
                .setUrls(url)
                .setScanners(
                        new SubTypesScanner(true),
                        new TypeAnnotationsScanner(),
                        new MethodAnnotationsScanner());
    }

    public Reflections make() {
        return new Reflections(configurationBuilder);
    }
}
