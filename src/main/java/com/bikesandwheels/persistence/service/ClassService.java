package com.bikesandwheels.persistence.service;

import com.bikesandwheels.persistence.model.Class;

public interface ClassService extends BaseService<Class> {
    Class getByName(String name);
}
