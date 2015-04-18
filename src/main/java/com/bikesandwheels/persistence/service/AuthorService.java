package com.bikesandwheels.persistence.service;

import com.bikesandwheels.persistence.model.Author;

import java.util.*;

public interface AuthorService {
    Author getByName(String name);
    Author save(Author bank);
    Iterable<Author> save(Iterable<Author> authors);
    Set<Author> getAll();
}
