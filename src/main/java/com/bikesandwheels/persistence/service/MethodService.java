package com.bikesandwheels.persistence.service;

import com.bikesandwheels.persistence.model.*;
import com.bikesandwheels.persistence.model.Class;

public interface MethodService extends BaseService<Method> {
    public Method getByFullSignature(Class aClass, String name, String signature, String returnType);
}
