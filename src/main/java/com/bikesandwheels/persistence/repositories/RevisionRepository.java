package com.bikesandwheels.persistence.repositories;

import com.bikesandwheels.persistence.model.*;
import com.bikesandwheels.persistence.model.Class;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface RevisionRepository extends CrudRepository<Revision, Long> {
    @Query("select a from Revision a where a.revisedClass = :revisedClass and a.revisedMethod = :revisedMethod and a.date = :date")
    Revision findByClassMethodAndDate(@Param("revisedClass") Class revisedClass, @Param("revisedMethod") Method revisedMethod, @Param("date") Date date);
}
