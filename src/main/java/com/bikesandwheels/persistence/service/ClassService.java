package com.bikesandwheels.persistence.service;

import com.bikesandwheels.persistence.model.Class;

import java.util.Set;

public interface ClassService {
    Class getByName(String name);
    Class save(Class bank);
    Iterable<Class> save(Iterable<Class> authors);
    Set<Class> getAll();
}
