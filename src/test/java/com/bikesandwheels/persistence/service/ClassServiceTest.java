package com.bikesandwheels.persistence.service;

import com.bikesandwheels.config.*;
import com.bikesandwheels.persistence.model.Class;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {AppConfig.class})
@ActiveProfiles({Profiles.LIVE, Profiles.DB_IN_MEMORY})
public class ClassServiceTest {
    @Autowired
    private ClassService classService;

    private Class aClass;

    @Before
    public void setUp() throws Exception {
        aClass = new Class();
        aClass.setCanonicalName("com.test.TestClass");
    }

    @Test
    public void saveTest() throws Exception {
        classService.save(aClass);
        assertNotNull(aClass.getClassId());
        assertTrue(classService.getAll().contains(aClass));
    }

    @Test
    public void mergeClassesWithSameNameTest() throws Exception {
        classService.save(aClass);
        classService.save(aClass);
        assertThat(classService.getAll().size(), is(1));
    }
}
