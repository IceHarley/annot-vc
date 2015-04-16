package com.bikesandwheels.persistence.dao;

import com.bikesandwheels.persistence.model.Method;
import org.springframework.data.repository.CrudRepository;

public interface MethodRepository extends CrudRepository<Method, Long> {
}
