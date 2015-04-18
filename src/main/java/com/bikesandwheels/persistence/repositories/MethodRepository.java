package com.bikesandwheels.persistence.repositories;

import com.bikesandwheels.persistence.model.Method;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MethodRepository extends CrudRepository<Method, Long> {
    @Query("select a from Method a where a.name = :name and a.signature = :signature")
    Method findByNameAndSignature(@Param("name") String name, @Param("signature") String signature);
}
