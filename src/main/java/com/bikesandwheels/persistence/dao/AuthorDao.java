package com.bikesandwheels.persistence.dao;

import com.bikesandwheels.persistence.model.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> list();

    void saveOrUpdate(Author user);

    void delete(int id);

    Author get(int id);
}
