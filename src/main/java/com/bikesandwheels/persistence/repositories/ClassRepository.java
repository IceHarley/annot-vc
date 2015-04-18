package com.bikesandwheels.persistence.repositories;

import com.bikesandwheels.persistence.model.Class;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ClassRepository extends CrudRepository<Class, Long> {
    @Query("select a from Class a where a.canonicalName = :name")
    Class findByName(@Param("name") String name);
}
