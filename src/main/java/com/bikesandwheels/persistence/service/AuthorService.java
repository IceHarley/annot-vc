package com.bikesandwheels.persistence.service;

import com.bikesandwheels.persistence.model.Author;

import java.util.*;

public interface AuthorService extends BaseService<Author> {
    Author getByName(String name);
}
