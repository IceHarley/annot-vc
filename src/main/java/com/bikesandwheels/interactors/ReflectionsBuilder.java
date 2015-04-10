package com.bikesandwheels.interactors;

import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.*;

public class ReflectionsBuilder {
    private final Class<?> baseClass;
    private ConfigurationBuilder configurationBuilder;

    public ReflectionsBuilder(Class<?> baseClass) {
        this.baseClass = baseClass;
        this.configurationBuilder = new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forClass(baseClass))
                .setScanners(
                        new SubTypesScanner(true),
                        new TypeAnnotationsScanner(),
                        new MethodAnnotationsScanner());
    }

    public Reflections make() {
        return new Reflections(configurationBuilder);
    }
    
    public ReflectionsBuilder limitToPackage() {
        //configurationBuilder.filterInputsBy(new FilterBuilder().include(getPackageFilter(baseClass)));
        return this;
    }

    private static String getPackageFilter(Class<?> baseClass) {
        return String.format("%s\\$.*", baseClass.getPackage());
    }

}
