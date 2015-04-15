package com.bikesandwheels.persistence.dao;

import com.bikesandwheels.config.AppConfig;
import com.bikesandwheels.persistence.model.Author;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {AppConfig.class})
@ActiveProfiles({"live", "db", "db-local"})
public class AuthorDaoTest {
    @Autowired
    private AuthorDao authorDao;
    Author author;

    @Before
    public void setUp() throws Exception {
        author = new Author();
        author.setName("John");
        author.setAuthorId(1L);
    }

    @Test
    public void saveTest() throws Exception {
        authorDao.save(author);
        assertThat(authorDao.findAll(), contains(author));
    }
}
