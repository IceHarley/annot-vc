package com.bikesandwheels.persistence.dao;

import com.bikesandwheels.persistence.model.Class;
import org.springframework.data.repository.CrudRepository;

public interface ClassRepository extends CrudRepository<Class, Long> {
}
