package com.bikesandwheels.persistence.repositories;

import com.bikesandwheels.persistence.model.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    @SuppressWarnings("JpaQlInspection")
    @Query("select a from Author a where a.name = :name")
    Author findByName(@Param("name") String name);
}