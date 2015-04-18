package com.bikesandwheels.persistence.service;

import com.bikesandwheels.persistence.model.*;
import com.bikesandwheels.persistence.model.Class;

import java.util.Set;

public interface MethodService extends BaseService<Method> {
    Method getByNameAndSignature(String name, String signature);
}
