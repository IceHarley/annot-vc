package com.bikesandwheels.persistence.service.impl;

import com.bikesandwheels.persistence.model.Method;
import com.bikesandwheels.persistence.repositories.MethodRepository;
import com.bikesandwheels.persistence.service.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class MethodServiceImpl extends BaseServiceImpl<Method> implements MethodService {
    @Autowired
    private MethodRepository methodRepository;

    @Override
    protected CrudRepository<Method, Long> getRepository() {
        return methodRepository;
    }

    public Method getByNameAndSignature(String name, String signature) {
        return methodRepository.findByNameAndSignature(name, signature);
    }

    @Override
    protected void fillIdIfExists(Method method) {
        if (method.getMethodId() == null) {
            Method existedMethod = getByNameAndSignature(method.getName(), method.getSignature());
            if (existedMethod != null)
                method.setMethodId(existedMethod.getMethodId());
        }
    }
}
