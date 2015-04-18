package com.bikesandwheels.persistence.service;

import com.bikesandwheels.persistence.model.Method;

public interface MethodService extends BaseService<Method> {
    Method getByNameAndSignature(String name, String signature);
}
