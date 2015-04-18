package com.bikesandwheels.persistence.service.impl;

import com.bikesandwheels.persistence.model.Author;
import com.bikesandwheels.persistence.repositories.AuthorRepository;
import com.bikesandwheels.persistence.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl extends BaseServiceImpl<Author> implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    protected CrudRepository<Author, Long> getRepository() {
        return authorRepository;
    }

    public Author getByName(String name) {
        return authorRepository.findByName(name);
    }

    protected void fillIdIfExists(Author author) {
        if (author.getAuthorId() == null) {
            Author existedAuthor = getByName(author.getName());
            if (existedAuthor != null)
                author.setAuthorId(existedAuthor.getAuthorId());
        }
    }
}
