package com.bikesandwheels.persistence.service.impl;

import com.bikesandwheels.persistence.model.Author;
import com.bikesandwheels.persistence.repositories.AuthorRepository;
import com.bikesandwheels.persistence.service.AuthorService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Author getByName(String name) {
        return authorRepository.findByName(name);
    }

    public Author save(Author author) {
        fillIdIfExists(author);
        return authorRepository.save(author);
    }

    private void fillIdIfExists(Author author) {
        if (author.getAuthorId() == null) {
            Author existedAuthor = getByName(author.getName());
            if (existedAuthor != null)
                author.setAuthorId(existedAuthor.getAuthorId());
        }
    }

    public Iterable<Author> save(Iterable<Author> authors) {
        for (Author author : authors)
            fillIdIfExists(author);
        return authorRepository.save(authors);
    }

    public Set<Author> getAll() {
        return Sets.newHashSet(authorRepository.findAll());
    }
}
