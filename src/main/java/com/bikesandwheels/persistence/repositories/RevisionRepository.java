package com.bikesandwheels.persistence.repositories;

import com.bikesandwheels.persistence.model.*;
import com.bikesandwheels.persistence.model.Class;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface RevisionRepository extends CrudRepository<Revision, Long> {
    @Query("select a from Revision a where (a.revisedClass = :revisedClass or (a.revisedClass is null and :revisedClass is null)) " +
            " and (a.revisedMethod = :revisedMethod or (a.revisedMethod is null and :revisedMethod is null)) and a.date = :date")
    Revision findByClassMethodAndDate(@Param("revisedClass") Class revisedClass, @Param("revisedMethod") Method revisedMethod, @Param("date") Date date);
}
