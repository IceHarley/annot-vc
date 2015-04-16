package com.bikesandwheels.persistence.repositories;

import com.bikesandwheels.config.AppConfig;
import com.bikesandwheels.persistence.model.Author;
import com.google.common.collect.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {AppConfig.class})
@ActiveProfiles({"live", "db-in-memory"})
public class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private RevisionRepository revisionRepository;
    Author author;

    @Before
    public void setUp() throws Exception {
        author = new Author();
        author.setName("John");
    }

    @Test
    public void saveTest() throws Exception {
        authorRepository.save(author);
        assertNotNull(author.getAuthorId());
        assertTrue(Sets.newHashSet(authorRepository.findAll()).contains(author));
    }
}
