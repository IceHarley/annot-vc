package com.bikesandwheels.persistence.service.impl;

import com.bikesandwheels.persistence.model.*;
import com.bikesandwheels.persistence.model.Class;
import com.bikesandwheels.persistence.repositories.*;
import com.bikesandwheels.persistence.service.ClassService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassRepository classRepository;

    public com.bikesandwheels.persistence.model.Class getByName(String name) {
        return classRepository.findByName(name);
    }

    public Class save(Class aClass) {
        fillIdIfExists(aClass);
        return classRepository.save(aClass);
    }

    private void fillIdIfExists(Class aClass) {
        if (aClass.getClassId() == null) {
            Class existedClass = getByName(aClass.getCanonicalName());
            if (existedClass != null)
                aClass.setClassId(existedClass.getClassId());
        }
    }

    public Iterable<Class> save(Iterable<Class> classes) {
        for (Class aClass : classes)
            fillIdIfExists(aClass);
        return classRepository.save(classes);
    }

    public Set<Class> getAll() {
        return Sets.newHashSet(classRepository.findAll());
    }
}
