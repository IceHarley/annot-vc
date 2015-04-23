package com.bikesandwheels.domain.annotation_wrappers;

import com.bikesandwheels.annotations.Author;

class AuthorWrapper {
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
