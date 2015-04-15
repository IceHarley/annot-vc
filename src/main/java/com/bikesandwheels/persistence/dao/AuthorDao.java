package com.bikesandwheels.persistence.dao;

import com.bikesandwheels.persistence.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorDao extends CrudRepository<Author, Long> {
}
