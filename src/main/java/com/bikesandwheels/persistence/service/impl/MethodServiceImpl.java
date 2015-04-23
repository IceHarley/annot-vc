package com.bikesandwheels.persistence.service.impl;

import com.bikesandwheels.persistence.model.Method;
import com.bikesandwheels.persistence.model.Class;
import com.bikesandwheels.persistence.repositories.MethodRepository;
import com.bikesandwheels.persistence.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class MethodServiceImpl extends BaseServiceImpl<Method> implements MethodService {
    @Autowired
    private MethodRepository methodRepository;
    @Autowired
    ClassService classService;

    @Override
    protected CrudRepository<Method, Long> getRepository() {
        return methodRepository;
    }

    public Method getByFullSignature(Class aClass, String name, String signature, String returnType) {
        return methodRepository.findByFullSignature(aClass, name, signature, returnType);
    }

    @Override
    protected void fillIdIfExists(Method method) {
        if (method.getMethodId() == null) {
            Method existedMethod = getByFullSignature(method);
            if (existedMethod != null)
                method.setMethodId(existedMethod.getMethodId());
        }
    }

    private Method getByFullSignature(Method method) {
        return getByFullSignature(
                method.getDeclaringClass(),
                method.getName(),
                method.getSignature(),
                method.getReturnType());
    }

    @Override
    public Method save(Method method) {
        classService.save(method.getDeclaringClass());
        return super.save(method);
    }
}
