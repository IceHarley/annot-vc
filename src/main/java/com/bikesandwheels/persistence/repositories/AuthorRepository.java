package com.bikesandwheels.persistence.repositories;

import com.bikesandwheels.persistence.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
