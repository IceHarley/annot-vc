package com.bikesandwheels.persistence.repositories;

import com.bikesandwheels.persistence.model.Method;
import com.bikesandwheels.persistence.model.Class;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MethodRepository extends CrudRepository<Method, Long> {
    @Query("select a from Method a where a.declaringClass = :declaringClass " +
            " and a.name = :name and a.signature = :signature and a.returnType = :returnType")
    Method findByFullSignature(@Param("declaringClass") Class declaringClass,
                              @Param("name") String name,
                              @Param("signature") String signature,
                              @Param("returnType") String returnType);
}
