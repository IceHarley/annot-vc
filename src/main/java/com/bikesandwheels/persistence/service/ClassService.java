package com.bikesandwheels.persistence.service;

import com.bikesandwheels.persistence.model.Class;

import java.util.Set;

public interface ClassService extends BaseService<Class> {
    Class getByName(String name);
}
