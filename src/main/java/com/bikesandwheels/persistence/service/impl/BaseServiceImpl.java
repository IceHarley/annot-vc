package com.bikesandwheels.persistence.service.impl;

import com.google.common.collect.Sets;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

public abstract class BaseServiceImpl<T> {
    protected abstract CrudRepository<T, Long> getRepository();

    protected abstract void fillIdIfExists(T entity);

    public T save(T entity) {
        fillIdIfExists(entity);
        return getRepository().save(entity);
    }

    public Iterable<T> save(Iterable<T> entities) {
        Collection<T> result = Sets.newHashSet();
        for (T entity : entities)
            result.add(save(entity));
        return result;
    }

    public Set<T> getAll() {
        return Sets.newHashSet(getRepository().findAll());
    }

    public void deleteAll() {
        getRepository().deleteAll();
    }
}
