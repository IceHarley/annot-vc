package com.bikesandwheels.persistence.service;

import java.util.Set;

public interface BaseService <T> {
    T save(T entity);
    Iterable<T> save(Iterable<T> entities);
    Set<T> getAll();
}
