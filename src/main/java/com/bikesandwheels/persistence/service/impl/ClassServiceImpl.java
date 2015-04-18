package com.bikesandwheels.persistence.service.impl;

import com.bikesandwheels.persistence.model.Class;
import com.bikesandwheels.persistence.repositories.ClassRepository;
import com.bikesandwheels.persistence.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl extends BaseServiceImpl<Class> implements ClassService {
    @Autowired
    private ClassRepository classRepository;

    @Override
    protected CrudRepository<Class, Long> getRepository() {
        return classRepository;
    }

    public com.bikesandwheels.persistence.model.Class getByName(String name) {
        return classRepository.findByName(name);
    }

    protected void fillIdIfExists(Class aClass) {
        if (aClass != null && aClass.getClassId() == null) {
            Class existedClass = getByName(aClass.getCanonicalName());
            if (existedClass != null)
                aClass.setClassId(existedClass.getClassId());
        }
    }
}
