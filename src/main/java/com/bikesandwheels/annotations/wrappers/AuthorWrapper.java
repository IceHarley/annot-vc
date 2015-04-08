package com.bikesandwheels.annotations.wrappers;

import com.bikesandwheels.annotations.Author;

public class AuthorWrapper {
    private String name;

    public AuthorWrapper(Author author) {
        if (author == null || author.value() == null)
            throw new NullPointerException();
        this.name = author.value();
    }

    public String getName() {
        return name;
    }
}
