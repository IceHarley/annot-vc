package com.bikesandwheels.interactors;

import com.bikesandwheels.domain.ClassesRevisedObjectsMap;
import com.bikesandwheels.persistence.converter.Converter;
import com.bikesandwheels.persistence.model.Revision;
import com.bikesandwheels.persistence.service.RevisionService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.*;
import java.util.*;

@Component
public class Scanner {
    @Autowired
    AnnotatedClassesSearcher annotatedClassesSearcher;
    @Autowired
    RevisedSearcher revisedSearcher;
    @Autowired
    Converter converter;
    @Autowired
    RevisionService revisionService;

    public void scanAndSave(String path) {
        save(convert(getRevisedObjects(getAnnotatedClasses(path))));
    }

    private void save(List<Revision> revisions) {
        revisionService.save(revisions);
    }

    private List<Revision> convert(ClassesRevisedObjectsMap map) {
        return converter.convert(map);
    }

    private ClassesRevisedObjectsMap getRevisedObjects(Set<Class<?>> annotatedClasses) {
        revisedSearcher.setClasses(annotatedClasses);
        return revisedSearcher.findAllRevisedObjects();
    }

    private Set<Class<?>> getAnnotatedClasses(String path) {
        Set<Class<?>> classes = Sets.newHashSet();
        try {
            URL url = new URL("file:" + path);
            annotatedClassesSearcher.setUrl(url);
            classes = annotatedClassesSearcher.search();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return classes;
    }
}
