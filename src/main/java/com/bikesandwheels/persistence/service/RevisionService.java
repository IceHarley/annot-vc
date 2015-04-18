package com.bikesandwheels.persistence.service;

import com.bikesandwheels.persistence.model.*;
import com.bikesandwheels.persistence.model.Class;

import java.util.Date;

public interface RevisionService extends BaseService<Revision> {
    Revision getByClassMethodAndDate(Class revisedClass, Method revisedMethod, Date date);
}
