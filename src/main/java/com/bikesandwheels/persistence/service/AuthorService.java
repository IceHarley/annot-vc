package com.bikesandwheels.persistence.service;

import com.bikesandwheels.persistence.model.Author;

public interface AuthorService extends BaseService<Author> {
    Author getByName(String name);
}
