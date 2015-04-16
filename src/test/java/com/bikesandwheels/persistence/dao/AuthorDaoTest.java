package com.bikesandwheels.persistence.dao;

import com.bikesandwheels.config.AppConfig;
import com.bikesandwheels.persistence.model.Author;
import com.google.common.collect.Lists;
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
public class AuthorDaoTest {
    @Autowired
    private AuthorDao authorRepository;
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
        assertTrue(Lists.newArrayList(authorRepository.findAll()).contains(author));
    }
}
