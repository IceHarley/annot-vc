package com.bikesandwheels.persistence.service;

import com.bikesandwheels.config.*;
import com.bikesandwheels.persistence.model.Author;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.persistence.PersistenceException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {AppConfig.class})
@ActiveProfiles({Profiles.LIVE, Profiles.DB_IN_MEMORY})
public class AuthorServiceTest {
    @Autowired
    private AuthorService authorService;

    private Author author;

    @Before
    public void setUp() throws Exception {
        author = new Author();
        author.setName("John");
    }

    @After
    public void tearDown() throws Exception {
        authorService.deleteAll();
    }

    @Test(expected = PersistenceException.class)
    public void authorWithoutNameTest() throws Exception {
        author.setName(null);
        authorService.save(author);
    }

    @Test
    public void saveTest() throws Exception {
        authorService.save(author);
        assertNotNull(author.getAuthorId());
        assertTrue(authorService.getAll().contains(author));
    }

    @Test
    public void mergeAuthorsWithSameNameTest() throws Exception {
        authorService.save(author);
        authorService.save(author);
        assertThat(authorService.getAll().size(), is(1));
    }
}
