package com.bikesandwheels.persistence.dao;

import com.bikesandwheels.persistence.model.Revision;
import org.springframework.data.repository.CrudRepository;

public interface RevisionRepository extends CrudRepository<Revision, Long> {
}
