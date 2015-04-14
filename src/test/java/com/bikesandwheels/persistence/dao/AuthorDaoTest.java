package com.bikesandwheels.persistence.dao;

import com.bikesandwheels.config.TestConfig;
import com.bikesandwheels.persistence.model.Author;
import org.hibernate.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestConfig.class})
public class AuthorDaoTest {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private AuthorDao authorDao;

    @Test
    public void saveTest() throws Exception {
        Author author = new Author();
        author.setName("John");
        Session session = (Session) sessionFactory.openSession();
        //Transaction tx = null;
        try {
            //tx = session.beginTransaction();
            authorDao.saveOrUpdate(author);
            //tx.commit();
        } catch (Exception e) {
            //if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
