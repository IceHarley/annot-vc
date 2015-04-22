package com.bikesandwheels.interactors;

import com.bikesandwheels.domain.ClassesRevisedObjectsMap;
import com.bikesandwheels.persistence.converter.Converter;
import com.bikesandwheels.persistence.model.Revision;
import com.bikesandwheels.persistence.service.RevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
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

    public void scanAndSave(URL url) {
        save(convert(getRevisedObjects(getAnnotatedClasses(url))));
    }

    private Set<Class<?>> getAnnotatedClasses(URL url) {
        annotatedClassesSearcher.setUrl(url);
        return annotatedClassesSearcher.search();
    }

    private ClassesRevisedObjectsMap getRevisedObjects(Set<Class<?>> annotatedClasses) {
        revisedSearcher.setClasses(annotatedClasses);
        return revisedSearcher.findAllRevisedObjects();
    }

    private List<Revision> convert(ClassesRevisedObjectsMap map) {
        return converter.convert(map);
    }

    private void save(List<Revision> revisions) {
        revisionService.save(revisions);
    }
}
